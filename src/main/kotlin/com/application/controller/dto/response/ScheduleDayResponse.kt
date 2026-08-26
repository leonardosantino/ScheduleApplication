package com.application.controller.dto.response

import com.application.domain.objects.ScheduleDay

data class ScheduleDayResponse(
    var periods: Map<Int, SchedulePeriodResponse>,
) {
    companion object {
        fun from(day: ScheduleDay) =
            ScheduleDayResponse(
                periods = day.periods.mapValues { SchedulePeriodResponse.from(it.value) },
            )
    }
}
