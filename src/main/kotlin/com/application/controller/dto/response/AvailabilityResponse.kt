package com.application.controller.dto.response

import com.application.domain.entity.Availability

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
