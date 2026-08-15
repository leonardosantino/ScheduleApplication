package com.application.controller.dto.request

import com.application.domain.entity.Availability

class AvailabilityRequest(
    var id: String,
    var days: MutableMap<String, AvailabilityDayRequest>,
) {
    fun toAvailability() =
        Availability(
            id = id,
            days = days.mapValues { it.value.toAvailabilityDay() }.toMutableMap(),
        )
}
