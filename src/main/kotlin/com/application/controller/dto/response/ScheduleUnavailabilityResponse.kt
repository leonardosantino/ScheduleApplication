package com.application.controller.dto.response

import com.application.domain.objects.ScheduleUnavailability

data class ScheduleUnavailabilityResponse(
    var dates: Map<Int, ScheduleUnavailabilityDateResponse>,
) {
    companion object {
        fun from(unavailability: ScheduleUnavailability?) =
            ScheduleUnavailabilityResponse(
                dates = unavailability?.dates?.mapValues { ScheduleUnavailabilityDateResponse.from(it.value) }.orEmpty(),
            )
    }
}
