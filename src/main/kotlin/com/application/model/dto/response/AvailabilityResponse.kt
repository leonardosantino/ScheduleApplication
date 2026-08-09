package com.application.model.dto.response

import com.application.domain.entity.Availability
import com.application.domain.entity.AvailabilityDay
import com.application.domain.entity.AvailabilityPeriod

data class AvailabilityResponse(
    var id: String?,
    var days: Map<String, AvailabilityDayResponse>?,
) {
    companion object {
        fun from(availability: Availability) =
            AvailabilityResponse(
                id = availability.id,
                days = availability.days?.mapValues { AvailabilityDayResponse.from(it.value) },
            )
    }
}

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
