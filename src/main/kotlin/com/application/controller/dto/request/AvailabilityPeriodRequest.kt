package com.application.controller.dto.request

import com.application.domain.objects.AvailabilityPeriod
import com.application.domain.objects.AvailabilityPeriodTime

class AvailabilityPeriodRequest(
    var time: AvailabilityPeriodTime,
) {
    fun toAvailabilityPeriod() =
        AvailabilityPeriod(
            time = time,
        )
}
