package com.application.controller.dto.request

import com.application.domain.objects.SchedulePeriod
import com.application.domain.objects.SchedulePeriodTime

class SchedulePeriodRequest(
    var time: SchedulePeriodTime,
) {
    fun toPeriod() =
        SchedulePeriod(
            time = time,
        )
}
