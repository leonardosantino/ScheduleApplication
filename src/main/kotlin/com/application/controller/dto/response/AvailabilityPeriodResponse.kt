package com.application.controller.dto.response

import com.application.domain.objects.AvailabilityPeriod
import com.application.domain.objects.AvailabilityPeriodTime

data class AvailabilityPeriodResponse(
    var time: AvailabilityPeriodTime,
) {
    companion object {
        fun from(period: AvailabilityPeriod) = AvailabilityPeriodResponse(period.time)
    }
}
