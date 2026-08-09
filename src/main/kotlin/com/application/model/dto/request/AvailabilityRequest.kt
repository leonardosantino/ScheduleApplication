package com.application.model.dto.request

import com.application.domain.entity.Availability
import com.application.domain.entity.AvailabilityDay
import com.application.domain.entity.AvailabilityPeriod

class AvailabilityRequest(
    var id: String,
    var days: MutableMap<String, AvailabilityDayRequest>?,
) {
    fun toAvailability() =
        Availability(
            id = id,
            days = days?.mapValues { it.value.toAvailabilityDay() }?.toMutableMap(),
        )
}

class AvailabilityDayRequest(
    var periods: MutableMap<Int, AvailabilityPeriodRequest>?,
) {
    fun toAvailabilityDay() =
        AvailabilityDay(
            periods = periods?.mapValues { it.value.toAvailabilityPeriod() }?.toMutableMap(),
        )
}

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
