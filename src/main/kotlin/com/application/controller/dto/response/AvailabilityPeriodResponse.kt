package com.application.controller.dto.response

import com.application.domain.objects.AvailabilityPeriod

data class AvailabilityPeriodResponse(
    var start: Int,
    var end: Int,
) {
    companion object {
        fun from(period: AvailabilityPeriod) =
            AvailabilityPeriodResponse(
                start = period.start,
                end = period.end,
            )
    }
}
