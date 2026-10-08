package com.application.util

import com.application.domain.entity.Schedule
import com.application.domain.objects.ScheduleAvailability
import com.application.domain.objects.SchedulePeriod
import java.time.LocalDate

object ScheduleAvailabilityUtil {
    private const val STEP = 30
    private val WEEK_DAY_KEYS = listOf("mon", "tue", "wed", "thu", "fri", "sat", "sun")

    fun calculate(
        schedule: Schedule,
        date: LocalDate,
        duration: Int,
        booked: Set<Int>,
        minTime: Int,
    ): List<ScheduleAvailability> {
        val blocked = booked + unavailableTimes(schedule, date)
        val size = duration / STEP

        return schedule.days
            ?.get(WEEK_DAY_KEYS[date.dayOfWeek.ordinal])
            ?.periods
            ?.values
            .orEmpty()
            .flatMap { period ->
                val range = period.times()

                range.mapIndexed { index, time -> ScheduleAvailability(time, range.drop(index).take(size)) }
            }.filter { it.time > minTime && it.times.size == size && it.times.none(blocked::contains) }
            .sortedBy { it.time }
    }

    private fun unavailableTimes(
        schedule: Schedule,
        date: LocalDate,
    ): Set<Int> =
        schedule.unavailability
            ?.dates
            ?.values
            .orEmpty()
            .filter { date in LocalDate.parse(it.startDate)..LocalDate.parse(it.endDate) }
            .flatMap { it.periods.values }
            .flatMap { it.times() }
            .toSet()

    private fun SchedulePeriod.times() = (time.start..time.end step STEP).toList()
}
