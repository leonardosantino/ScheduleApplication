package com.application.controller.dto.request

import com.application.domain.entity.Schedule
import java.time.Instant

class ScheduleRequest(
    var id: String,
    var days: MutableMap<String, ScheduleDayRequest>,
) {
    fun toCreateSchedule() =
        Schedule(
            id = id,
            days = days.mapValues { it.value.toDay() }.toMutableMap(),
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
        )

    fun toUpdateSchedule(schedule: Schedule) =
        Schedule(
            id = schedule.id,
            days = days.mapValues { it.value.toDay() }.toMutableMap(),
            createdAt = schedule.createdAt,
            updatedAt = Instant.now(),
        )
}
