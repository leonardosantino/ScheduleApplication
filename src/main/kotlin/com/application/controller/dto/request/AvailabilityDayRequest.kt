package com.application.controller.dto.request

import com.application.domain.objects.AvailabilityDay

class AvailabilityDayRequest(
    var periods: MutableMap<Int, AvailabilityPeriodRequest>?,
) {
    fun toAvailabilityDay() =
        AvailabilityDay(
            periods = periods?.mapValues { it.value.toAvailabilityPeriod() }?.toMutableMap(),
        )
}
