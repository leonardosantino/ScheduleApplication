package com.application.service

import com.application.ApplicationTests
import com.application.common.constants.ExMessage
import com.application.common.constants.Zone
import com.application.controller.dto.request.AppointmentCancellationRequest
import com.application.controller.dto.request.AppointmentsQueryRequest
import com.application.controller.dto.request.CancellationRequest
import com.application.domain.objects.AppointmentScope
import com.application.domain.objects.AppointmentStatus
import com.application.domain.objects.UserRole
import com.application.exception.BadRequestException
import com.application.exception.NotFoundException
import com.application.fixture.AppointmentFixture
import com.application.fixture.AppointmentRequestFixture
import com.application.fixture.AppointmentRequestFixture.PROVIDER_ID
import com.application.repository.AppointmentRepository
import com.application.repository.RelCustomerProviderRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class AppointmentServiceTests : ApplicationTests() {
    @Autowired
    private lateinit var appointmentService: AppointmentService

    @Autowired
    private lateinit var appointmentRepository: AppointmentRepository

    @Autowired
    private lateinit var relCustomerProviderRepository: RelCustomerProviderRepository

    private val date = LocalDate.of(2030, 1, 7)
    private val nextDate = LocalDate.of(2030, 1, 8)
    private val today = LocalDate.now(Zone.ID_AMERICA_SAO_PAULO)

    private fun query(
        scope: AppointmentScope,
        size: Int = 10,
    ) = AppointmentsQueryRequest(scope = scope, page = 0, size = size)

    private fun cancellation() =
        AppointmentCancellationRequest(
            CancellationRequest(reason = "Imprevisto no horário", canceledBy = UserRole.CUSTOMER.name),
        )

    @BeforeEach
    fun cleanUp() {
        appointmentRepository.deleteAll()
        relCustomerProviderRepository.deleteAll()
    }

    @Test
    fun shouldSaveAppointment() {
        val request = AppointmentRequestFixture.appointment(date, listOf(780, 810))

        val appointment = appointmentService.save(request)

        assertNotNull(appointment.id)
        assertEquals(request.customer.id, appointment.customer.id)
        assertEquals(request.provider.id, appointment.provider.id)
        assertEquals(request.service.id, appointment.service.id)
        assertEquals(date.toString(), appointment.date)
        assertEquals(780, appointment.time)
        assertEquals(listOf(780, 810), appointment.times)
        assertEquals(AppointmentStatus.CONFIRMED.value, appointment.status)
        assertNull(appointment.cancellation)
    }

    @Test
    fun shouldCreateRelCustomerProviderOnSave() {
        val request = AppointmentRequestFixture.appointment(date, listOf(780))

        appointmentService.save(request)

        val rel = relCustomerProviderRepository.findByCustomerIdAndProviderId(request.customer.id, request.provider.id)

        assertEquals(request.provider.slug, rel.get().provider.slug)
    }

    @Test
    fun shouldUpdateRelCustomerProviderWhenExists() {
        val request = AppointmentRequestFixture.appointment(date, listOf(780))
        appointmentService.save(request)
        val rel = relCustomerProviderRepository.findByCustomerIdAndProviderId(request.customer.id, request.provider.id).get()

        appointmentService.save(AppointmentRequestFixture.appointment(nextDate, listOf(780)))

        val updated = relCustomerProviderRepository.findByCustomerIdAndProviderId(request.customer.id, request.provider.id).get()

        assertEquals(1, relCustomerProviderRepository.count())
        assertEquals(rel.id, updated.id)
        assertEquals(rel.createdAt.toEpochMilli(), updated.createdAt.toEpochMilli())
        assertNotEquals(rel.updatedAt.toEpochMilli(), updated.updatedAt.toEpochMilli())
    }

    @Test
    fun shouldThrowWhenCustomerAlreadyScheduledWithProviderOnDate() {
        appointmentService.save(AppointmentRequestFixture.appointment(date, listOf(780)))

        val exception =
            assertFailsWith<BadRequestException> {
                appointmentService.save(AppointmentRequestFixture.appointment(date, listOf(900)))
            }

        assertEquals(ExMessage.APPOINTMENT_ALREADY_SCHEDULED, exception.message)
    }

    @Test
    fun shouldThrowWhenTimeUnavailable() {
        appointmentService.save(AppointmentRequestFixture.appointment(date, listOf(780)))

        val exception =
            assertFailsWith<BadRequestException> {
                appointmentService.save(
                    AppointmentRequestFixture.appointment(date, listOf(780), customer = AppointmentRequestFixture.otherCustomer),
                )
            }

        assertEquals(ExMessage.APPOINTMENT_TIME_UNAVAILABLE, exception.message)
    }

    @Test
    fun shouldAllowSameCustomerOnAnotherDate() {
        appointmentService.save(AppointmentRequestFixture.appointment(date, listOf(780)))

        val appointment = appointmentService.save(AppointmentRequestFixture.appointment(nextDate, listOf(780)))

        assertEquals(nextDate.toString(), appointment.date)
    }

    @Test
    fun shouldAllowSameTimeAfterCancellation() {
        val first = appointmentService.save(AppointmentRequestFixture.appointment(date, listOf(780)))
        appointmentService.cancel(first.id.toString(), cancellation())

        val appointment =
            appointmentService.save(
                AppointmentRequestFixture.appointment(date, listOf(780), customer = AppointmentRequestFixture.otherCustomer),
            )

        assertNotEquals(first.id, appointment.id)
    }

    @Test
    fun shouldCancelAppointment() {
        val appointment = appointmentService.save(AppointmentRequestFixture.appointment(date, listOf(780)))
        val request = cancellation()

        val canceled = appointmentService.cancel(appointment.id.toString(), request)

        assertEquals(AppointmentStatus.CANCELED.value, canceled.status)
        assertEquals(request.cancellation.reason, canceled.cancellation?.reason)
        assertEquals(request.cancellation.canceledBy, canceled.cancellation?.canceledBy)
        assertNotNull(canceled.cancellation?.canceledAt)
        assertEquals(appointment.createdAt.toEpochMilli(), canceled.createdAt.toEpochMilli())
        assertNotEquals(appointment.updatedAt.toEpochMilli(), canceled.updatedAt.toEpochMilli())
    }

    @Test
    fun shouldThrowNotFoundWhenCancelingUnknownAppointment() {
        val exception = assertFailsWith<NotFoundException> { appointmentService.cancel("unknown", cancellation()) }

        assertEquals(ExMessage.APPOINTMENT_NOT_FOUND, exception.message)
    }

    @Test
    fun shouldFindOnlyConfirmedOfProviderAndDate() {
        val confirmed = appointmentRepository.save(AppointmentFixture.appointment(date, listOf(780)))
        appointmentRepository.save(AppointmentFixture.appointment(date, listOf(840), AppointmentStatus.CANCELED))
        appointmentRepository.save(AppointmentFixture.appointment(nextDate, listOf(900)))
        appointmentRepository.save(
            AppointmentRequestFixture
                .appointment(date, listOf(960), provider = AppointmentRequestFixture.otherProvider)
                .toCreate(),
        )

        val appointments = appointmentService.findAllByProviderIdAndDateAndStatusConfirmed(PROVIDER_ID, date)

        assertEquals(listOf(confirmed.id), appointments.map { it.id })
    }

    @Test
    fun shouldFindUpcomingByCustomerId() {
        val upcoming = appointmentRepository.save(AppointmentFixture.appointment(today.plusDays(1), listOf(780)))
        appointmentRepository.save(AppointmentFixture.appointment(today.minusDays(1), listOf(780)))

        val page = appointmentService.findByCustomerId(AppointmentRequestFixture.customer.id, query(AppointmentScope.UPCOMING))

        assertEquals(listOf(upcoming.id), page.content.map { it.id })
    }

    @Test
    fun shouldFindPastByCustomerId() {
        appointmentRepository.save(AppointmentFixture.appointment(today.plusDays(1), listOf(780)))
        val older = appointmentRepository.save(AppointmentFixture.appointment(today.minusDays(2), listOf(780)))
        val past = appointmentRepository.save(AppointmentFixture.appointment(today.minusDays(1), listOf(780)))

        val page = appointmentService.findByCustomerId(AppointmentRequestFixture.customer.id, query(AppointmentScope.PAST))

        assertEquals(listOf(past.id, older.id), page.content.map { it.id })
    }

    @Test
    fun shouldFindByProviderId() {
        val upcoming = appointmentRepository.save(AppointmentFixture.appointment(today.plusDays(1), listOf(780)))
        appointmentRepository.save(
            AppointmentRequestFixture
                .appointment(today.plusDays(1), listOf(780), provider = AppointmentRequestFixture.otherProvider)
                .toCreate(),
        )

        val page = appointmentService.findByProviderId(PROVIDER_ID, query(AppointmentScope.UPCOMING))

        assertEquals(listOf(upcoming.id), page.content.map { it.id })
    }

    @Test
    fun shouldPaginate() {
        val first = appointmentRepository.save(AppointmentFixture.appointment(today.plusDays(1), listOf(780)))
        val second = appointmentRepository.save(AppointmentFixture.appointment(today.plusDays(2), listOf(780)))
        appointmentRepository.save(AppointmentFixture.appointment(today.plusDays(3), listOf(780)))

        val page = appointmentService.findByCustomerId(AppointmentRequestFixture.customer.id, query(AppointmentScope.UPCOMING, size = 2))

        assertEquals(3, page.totalElements)
        assertEquals(2, page.totalPages)
        assertEquals(listOf(first.id, second.id), page.content.map { it.id })
    }
}
