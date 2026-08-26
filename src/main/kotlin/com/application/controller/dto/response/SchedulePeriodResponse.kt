package com.application.controller.dto.response

import com.application.domain.objects.SchedulePeriod
import com.application.domain.objects.SchedulePeriodTime

data class SchedulePeriodResponse(
    var time: SchedulePeriodTime,
) {
    companion object {
        fun from(period: SchedulePeriod) = SchedulePeriodResponse(period.time)
    }
}
