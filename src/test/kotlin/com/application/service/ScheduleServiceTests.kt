package com.application.service

import com.application.ApplicationTests
import com.application.common.constants.ExMessage
import com.application.domain.objects.AppointmentStatus
import com.application.exception.NotFoundException
import com.application.fixture.AppointmentFixture
import com.application.fixture.ScheduleRequestFixture
import com.application.fixture.ScheduleRequestFixture.PROVIDER_ID
import com.application.repository.AppointmentRepository
import com.application.repository.ScheduleRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ScheduleServiceTests : ApplicationTests() {
    @Autowired
    private lateinit var scheduleService: ScheduleService

    @Autowired
    private lateinit var scheduleRepository: ScheduleRepository

    @Autowired
    private lateinit var appointmentRepository: AppointmentRepository

    private val monday = LocalDate.of(2030, 1, 7)
    private val nextMonday = LocalDate.of(2030, 1, 14)
    private val tuesday = LocalDate.of(2030, 1, 8)

    private fun appointment(
        date: LocalDate,
        times: List<Int>,
        status: AppointmentStatus,
    ) = appointmentRepository.save(AppointmentFixture.appointment(date, times, status))

    @BeforeEach
    fun cleanUp() {
        scheduleRepository.deleteAll()
        appointmentRepository.deleteAll()
    }

    @Test
    fun shouldCreateWhenNotExists() {
        val schedule = scheduleService.save(ScheduleRequestFixture.mondayMorning)

        assertEquals(PROVIDER_ID, schedule.id)
        assertEquals(
            480,
            schedule.days
                ?.get("mon")
                ?.periods
                ?.get(1)
                ?.time
                ?.start,
        )
        assertEquals(
            720,
            schedule.days
                ?.get("mon")
                ?.periods
                ?.get(1)
                ?.time
                ?.end,
        )
        assertNotNull(schedule.createdAt)
    }

    @Test
    fun shouldUpdateDaysKeepingUnavailabilityWhenExists() {
        val created = scheduleService.save(ScheduleRequestFixture.unavailability(Triple("2030-01-07", "2030-01-07", 480 to 600)))

        val updated = scheduleService.save(ScheduleRequestFixture.mondayMorning)

        assertEquals(1, updated.unavailability?.dates?.size)
        assertEquals(
            480,
            updated.days
                ?.get("mon")
                ?.periods
                ?.get(1)
                ?.time
                ?.start,
        )
        assertEquals(created.createdAt?.toEpochMilli(), updated.createdAt?.toEpochMilli())
        assertNotEquals(created.updatedAt?.toEpochMilli(), updated.updatedAt?.toEpochMilli())
    }

    @Test
    fun shouldUpdateUnavailabilityKeepingDaysWhenExists() {
        scheduleService.save(ScheduleRequestFixture.mondayMorning)

        val updated = scheduleService.save(ScheduleRequestFixture.unavailability(Triple("2030-01-07", "2030-01-07", 480 to 600)))

        assertEquals(1, updated.unavailability?.dates?.size)
        assertEquals(
            480,
            updated.days
                ?.get("mon")
                ?.periods
                ?.get(1)
                ?.time
                ?.start,
        )
    }

    @Test
    fun shouldReplaceUnavailabilityDatesOnUpdate() {
        scheduleService.save(
            ScheduleRequestFixture.unavailability(
                Triple("2030-01-07", "2030-01-07", 480 to 600),
                Triple("2030-01-14", "2030-01-14", 480 to 600),
            ),
        )

        val updated = scheduleService.save(ScheduleRequestFixture.unavailability(Triple("2030-01-21", "2030-01-21", 480 to 600)))

        assertEquals(1, updated.unavailability?.dates?.size)
        assertEquals(
            "2030-01-21",
            updated.unavailability
                ?.dates
                ?.values
                ?.first()
                ?.startDate,
        )
    }

    @Test
    fun shouldFindByProviderId() {
        scheduleService.save(ScheduleRequestFixture.mondayMorning)

        val schedule = scheduleService.findByProviderId(PROVIDER_ID)

        assertEquals(PROVIDER_ID, schedule.id)
    }

    @Test
    fun shouldThrowNotFoundWhenScheduleDoesNotExist() {
        val exception = assertFailsWith<NotFoundException> { scheduleService.findByProviderId(PROVIDER_ID) }

        assertEquals(ExMessage.SCHEDULE_NOT_FOUND, exception.message)
    }

    @Test
    fun shouldReturnAllTimesWhenNoRestrictions() {
        scheduleService.save(ScheduleRequestFixture.mondayMorning)

        val availability = scheduleService.findAvailability(PROVIDER_ID, monday, 30)

        assertEquals(listOf(480, 510, 540, 570, 600, 630, 660, 690), availability.map { it.time })
        assertTrue(availability.all { it.times.size == 1 })
    }

    @Test
    fun shouldReturnEmptyWhenDayHasNoPeriods() {
        scheduleService.save(ScheduleRequestFixture.mondayMorning)

        val availability = scheduleService.findAvailability(PROVIDER_ID, tuesday, 30)

        assertTrue(availability.isEmpty())
    }

    @Test
    fun shouldFitServiceDuration() {
        scheduleService.save(ScheduleRequestFixture.mondayMorning)

        val availability = scheduleService.findAvailability(PROVIDER_ID, monday, 60)

        assertEquals(listOf(480, 510, 540, 570, 600, 630, 660), availability.map { it.time })
        assertEquals(listOf(660, 690), availability.last().times)
    }

    @Test
    fun shouldExcludeBookedTimes() {
        scheduleService.save(ScheduleRequestFixture.mondayMorning)
        appointment(monday, listOf(540, 570), AppointmentStatus.CONFIRMED)

        val availability = scheduleService.findAvailability(PROVIDER_ID, monday, 60)

        assertEquals(listOf(480, 600, 630, 660), availability.map { it.time })
    }

    @Test
    fun shouldIgnoreCanceledAppointments() {
        scheduleService.save(ScheduleRequestFixture.mondayMorning)
        appointment(monday, listOf(540), AppointmentStatus.CANCELED)

        val availability = scheduleService.findAvailability(PROVIDER_ID, monday, 30)

        assertTrue(availability.any { it.time == 540 })
    }

    @Test
    fun shouldIgnoreAppointmentsOfOtherDate() {
        scheduleService.save(ScheduleRequestFixture.mondayMorning)
        appointment(nextMonday, listOf(540), AppointmentStatus.CONFIRMED)

        val availability = scheduleService.findAvailability(PROVIDER_ID, monday, 30)

        assertTrue(availability.any { it.time == 540 })
    }

    @Test
    fun shouldExcludeUnavailablePeriodOnSingleDate() {
        scheduleService.save(ScheduleRequestFixture.mondayMorning)
        scheduleService.save(ScheduleRequestFixture.unavailability(Triple("2030-01-07", "2030-01-07", 480 to 600)))

        val availability = scheduleService.findAvailability(PROVIDER_ID, monday, 30)

        assertEquals(listOf(600, 630, 660, 690), availability.map { it.time })
    }

    @Test
    fun shouldExcludeUnavailabilityWithinDateRange() {
        scheduleService.save(ScheduleRequestFixture.mondayMorning)
        scheduleService.save(ScheduleRequestFixture.unavailability(Triple("2030-01-05", "2030-01-09", 480 to 720)))

        val availability = scheduleService.findAvailability(PROVIDER_ID, monday, 30)

        assertTrue(availability.isEmpty())
    }

    @Test
    fun shouldNotExcludeWhenDateOutsideUnavailabilityRange() {
        scheduleService.save(ScheduleRequestFixture.mondayMorning)
        scheduleService.save(ScheduleRequestFixture.unavailability(Triple("2030-01-07", "2030-01-09", 480 to 720)))

        val availability = scheduleService.findAvailability(PROVIDER_ID, nextMonday, 30)

        assertEquals(8, availability.size)
    }

    @Test
    fun shouldCombineBookedAndUnavailable() {
        scheduleService.save(ScheduleRequestFixture.mondayMorning)
        scheduleService.save(ScheduleRequestFixture.unavailability(Triple("2030-01-07", "2030-01-07", 480 to 600)))
        appointment(monday, listOf(630), AppointmentStatus.CONFIRMED)

        val availability = scheduleService.findAvailability(PROVIDER_ID, monday, 30)

        assertEquals(listOf(600, 660, 690), availability.map { it.time })
    }
}
