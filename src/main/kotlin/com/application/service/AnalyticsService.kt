package com.application.service

import com.application.common.constants.Zone
import com.application.common.math.divide
import com.application.common.math.percentage
import com.application.common.math.sum
import com.application.controller.dto.response.AnalyticsBucketResponse
import com.application.controller.dto.response.AnalyticsCancellationsResponse
import com.application.controller.dto.response.AnalyticsResponse
import com.application.controller.dto.response.AnalyticsSeriesPointResponse
import com.application.controller.dto.response.AnalyticsServiceResponse
import com.application.controller.dto.response.AnalyticsSummaryResponse
import com.application.controller.dto.response.AnalyticsUpcomingResponse
import com.application.domain.entity.Appointment
import com.application.domain.objects.AppointmentStatus
import com.application.domain.objects.UserRole
import com.application.repository.AppointmentRepository
import com.application.repository.RelCustomerProviderRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.DayOfWeek
import java.time.LocalDate

@Service
class AnalyticsService(
    private val appointmentRepository: AppointmentRepository,
    private val relCustomerProviderRepository: RelCustomerProviderRepository,
) {
    companion object {
        const val UPCOMING_DAYS = 15
        const val MINUTES_IN_HOUR = 60
        const val SERVICES_LIMIT = 5
    }

    fun analyze(
        providerId: String,
        from: LocalDate,
        to: LocalDate,
    ): AnalyticsResponse {
        val previousFrom = from.minusMonths(1)
        val previousTo = to.minusMonths(1)

        val current = appointmentRepository.findByPeriod(providerId, from.toString(), to.toString())
        val previous = appointmentRepository.findByPeriod(providerId, previousFrom.toString(), previousTo.toString())

        val confirmed = current.filter { it.isConfirmed() }
        val canceled = current.filter { it.isCanceled() }

        return AnalyticsResponse(
            from = from.toString(),
            to = to.toString(),
            previousFrom = previousFrom.toString(),
            previousTo = previousTo.toString(),
            summary = summarize(providerId, current, from, to),
            previous = summarize(providerId, previous, previousFrom, previousTo),
            series = series(confirmed, from, to),
            topServices = topServices(confirmed),
            byWeekday = byWeekday(confirmed),
            byHour = byHour(confirmed),
            cancellations =
                AnalyticsCancellationsResponse(
                    total = canceled.size,
                    byCustomer = canceled.count { it.isCanceledBy(UserRole.CUSTOMER) },
                    byProvider = canceled.count { it.isCanceledBy(UserRole.PROVIDER) },
                    lostRevenue = canceled.revenue(),
                ),
            upcoming = upcoming(providerId),
            totalCustomers = relCustomerProviderRepository.countByProviderId(providerId).toInt(),
        )
    }

    private fun upcoming(providerId: String): AnalyticsUpcomingResponse {
        val today = LocalDate.now(Zone.ID_AMERICA_SAO_PAULO)
        val items =
            appointmentRepository
                .findByPeriod(providerId, today.plusDays(1).toString(), today.plusDays(UPCOMING_DAYS.toLong()).toString())
                .filter { it.isConfirmed() }

        return AnalyticsUpcomingResponse(days = UPCOMING_DAYS, appointments = items.size, revenue = items.revenue())
    }

    private fun summarize(
        providerId: String,
        appointments: List<Appointment>,
        from: LocalDate,
        to: LocalDate,
    ): AnalyticsSummaryResponse {
        val confirmed = appointments.filter { it.isConfirmed() }
        val canceled = appointments.filter { it.isCanceled() }

        val newCustomers =
            relCustomerProviderRepository.countByProviderIdAndCreatedAtBetween(
                providerId,
                from.atStartOfDay(Zone.ID_AMERICA_SAO_PAULO).toInstant(),
                to.plusDays(1).atStartOfDay(Zone.ID_AMERICA_SAO_PAULO).toInstant(),
            )

        return AnalyticsSummaryResponse(
            realizedRevenue = confirmed.revenue(),
            lostRevenue = canceled.revenue(),
            appointments = confirmed.size,
            averageTicket = divide(confirmed.revenue(), BigDecimal(confirmed.size)),
            cancellations = canceled.size,
            cancellationRate = percentage(canceled.size, appointments.size),
            newCustomers = newCustomers.toInt(),
        )
    }

    private fun series(
        confirmed: List<Appointment>,
        from: LocalDate,
        to: LocalDate,
    ): List<AnalyticsSeriesPointResponse> {
        val grouped = confirmed.groupBy { it.date }

        return generateSequence(from) { it.plusDays(1) }
            .takeWhile { !it.isAfter(to) }
            .map { day ->
                val items = grouped[day.toString()].orEmpty()
                AnalyticsSeriesPointResponse(key = day.toString(), revenue = items.revenue(), appointments = items.size)
            }.toList()
    }

    private fun topServices(confirmed: List<Appointment>): List<AnalyticsServiceResponse> =
        confirmed
            .groupBy { it.service.id }
            .map { (id, items) ->
                AnalyticsServiceResponse(
                    id = id,
                    name = items.first().service.name,
                    appointments = items.size,
                    revenue = items.revenue(),
                )
            }.sortedWith(compareByDescending<AnalyticsServiceResponse> { it.revenue }.thenByDescending { it.appointments })
            .take(SERVICES_LIMIT)

    private fun byWeekday(confirmed: List<Appointment>): List<AnalyticsBucketResponse> {
        val grouped = confirmed.groupBy { LocalDate.parse(it.date).dayOfWeek }

        return DayOfWeek.entries.map { day ->
            val items = grouped[day].orEmpty()
            AnalyticsBucketResponse(key = day.value, appointments = items.size, revenue = items.revenue())
        }
    }

    private fun byHour(confirmed: List<Appointment>): List<AnalyticsBucketResponse> =
        confirmed
            .groupBy { it.time / MINUTES_IN_HOUR }
            .map { (hour, items) ->
                AnalyticsBucketResponse(key = hour, appointments = items.size, revenue = items.revenue())
            }.sortedBy { it.key }

    private fun Appointment.isConfirmed() = status == AppointmentStatus.CONFIRMED.value

    private fun Appointment.isCanceled() = status == AppointmentStatus.CANCELED.value

    private fun Appointment.isCanceledBy(role: UserRole) = cancellation?.canceledBy == role.name

    private fun List<Appointment>.revenue(): BigDecimal = sum(map { it.service.value })
}
