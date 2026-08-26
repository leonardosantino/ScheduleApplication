package com.application.controller.dto.request

import com.application.domain.objects.ScheduleDay

class ScheduleDayRequest(
    var periods: MutableMap<Int, SchedulePeriodRequest>,
) {
    fun toDay() =
        ScheduleDay(
            periods = periods.mapValues { it.value.toPeriod() }.toMutableMap(),
        )
}
