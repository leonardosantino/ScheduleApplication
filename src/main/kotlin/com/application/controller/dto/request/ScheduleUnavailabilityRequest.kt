package com.application.controller.dto.request

import com.application.domain.objects.ScheduleUnavailability
import kotlin.collections.toMutableMap

class ScheduleUnavailabilityRequest(
    var dates: MutableMap<Int, ScheduleUnavailabilityDateRequest>,
) {
    fun toUnavailability() =
        ScheduleUnavailability(
            dates =
                dates
                    .values
                    .mapIndexed { i, it -> i to it.toUnavailabilityDate() }
                    .toMap()
                    .toMutableMap(),
        )
}
