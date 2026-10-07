package com.application.controller.dto.request

import com.application.domain.objects.ScheduleUnavailabilityDate

class ScheduleUnavailabilityDateRequest(
    var startDate: String,
    var endDate: String,
    var periods: MutableMap<Int, SchedulePeriodRequest>,
) {
    fun toUnavailabilityDate() =
        ScheduleUnavailabilityDate(
            startDate = startDate,
            endDate = endDate,
            periods = periods.mapValues { it.value.toPeriod() }.toMutableMap(),
        )
}
