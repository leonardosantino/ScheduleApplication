package com.application.controller.dto.response

import com.application.domain.objects.AvailabilityDay

data class AvailabilityDayResponse(
    var periods: Map<Int, AvailabilityPeriodResponse>?,
) {
    companion object {
        fun from(day: AvailabilityDay) =
            AvailabilityDayResponse(
                periods = day.periods?.mapValues { AvailabilityPeriodResponse.from(it.value) },
            )
    }
}
