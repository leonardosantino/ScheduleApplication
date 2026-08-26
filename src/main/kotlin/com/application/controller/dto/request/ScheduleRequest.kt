package com.application.controller.dto.request

import com.application.domain.entity.Schedule

class ScheduleRequest(
    var id: String,
    var days: MutableMap<String, ScheduleDayRequest>,
) {
    fun toSchedule() =
        Schedule(
            id = id,
            days = days.mapValues { it.value.toDay() }.toMutableMap(),
        )
}
