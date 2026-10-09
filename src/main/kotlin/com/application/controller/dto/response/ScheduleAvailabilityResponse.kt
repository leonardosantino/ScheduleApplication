package com.application.controller.dto.response

import com.application.domain.objects.ScheduleAvailability

data class ScheduleAvailabilityResponse(
    val items: List<ScheduleAvailabilityItemResponse>,
) {
    companion object {
        fun from(availability: List<ScheduleAvailability>) =
            ScheduleAvailabilityResponse(availability.map { ScheduleAvailabilityItemResponse(it.time, it.times) })
    }
}
