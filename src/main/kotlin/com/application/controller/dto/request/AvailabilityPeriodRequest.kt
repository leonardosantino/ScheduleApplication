package com.application.controller.dto.request

import com.application.domain.objects.AvailabilityPeriod

class AvailabilityPeriodRequest(
    var start: Int,
    var end: Int,
) {
    fun toAvailabilityPeriod() =
        AvailabilityPeriod(
            start = start,
            end = end,
        )
}
