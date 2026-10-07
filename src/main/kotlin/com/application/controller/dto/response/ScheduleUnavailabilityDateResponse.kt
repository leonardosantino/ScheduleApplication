package com.application.controller.dto.response

import com.application.domain.objects.ScheduleUnavailabilityDate

data class ScheduleUnavailabilityDateResponse(
    var startDate: String,
    var endDate: String,
    var periods: Map<Int, SchedulePeriodResponse>,
) {
    companion object {
        fun from(unavailability: ScheduleUnavailabilityDate) =
            ScheduleUnavailabilityDateResponse(
                startDate = unavailability.startDate,
                endDate = unavailability.endDate,
                periods = unavailability.periods.mapValues { SchedulePeriodResponse.from(it.value) },
            )
    }
}
