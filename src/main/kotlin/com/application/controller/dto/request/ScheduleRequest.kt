package com.application.controller.dto.request

import com.application.domain.entity.Schedule
import java.time.Instant

data class ScheduleRequest(
    var id: String,
    var days: MutableMap<String, ScheduleDayRequest>?,
    var unavailability: ScheduleUnavailabilityRequest?,
) {
    fun toCreateSchedule() =
        Schedule(
            id = id,
            days = days?.mapValues { it.value.toDay() }?.toMutableMap(),
            unavailability = unavailability?.toUnavailability(),
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
        )

    fun toUpdateSchedule(schedule: Schedule): Schedule {
        days?.let { schedule.days = days?.mapValues { day -> day.value.toDay() }?.toMutableMap() }
        unavailability?.let { schedule.unavailability = unavailability?.toUnavailability() }

        schedule.updatedAt = Instant.now()

        return schedule
    }
}
