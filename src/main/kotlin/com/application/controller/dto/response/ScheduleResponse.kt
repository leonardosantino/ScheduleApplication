package com.application.controller.dto.response

import com.application.domain.entity.Schedule

data class ScheduleResponse(
    var id: String,
    var days: Map<String, ScheduleDayResponse>?,
) {
    companion object {
        fun from(schedule: Schedule) =
            ScheduleResponse(
                id = schedule.id,
                days = schedule.days.mapValues { ScheduleDayResponse.from(it.value) },
            )
    }
}
