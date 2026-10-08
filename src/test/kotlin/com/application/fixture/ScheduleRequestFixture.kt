package com.application.fixture

import com.application.controller.dto.request.ScheduleDayRequest
import com.application.controller.dto.request.SchedulePeriodRequest
import com.application.controller.dto.request.ScheduleRequest
import com.application.controller.dto.request.ScheduleUnavailabilityDateRequest
import com.application.controller.dto.request.ScheduleUnavailabilityRequest
import com.application.domain.objects.SchedulePeriodTime

object ScheduleRequestFixture {
    const val PROVIDER_ID = "6abee7ddc47b109c4a211592"

    private fun period(
        start: Int,
        end: Int,
    ) = SchedulePeriodRequest(SchedulePeriodTime(start, end))

    val mondayMorning =
        ScheduleRequest(
            id = PROVIDER_ID,
            days = mutableMapOf("mon" to ScheduleDayRequest(mutableMapOf(1 to period(480, 720)))),
            unavailability = null,
        )

    fun unavailability(vararg dates: Triple<String, String, Pair<Int, Int>>) =
        ScheduleRequest(
            id = PROVIDER_ID,
            days = null,
            unavailability =
                ScheduleUnavailabilityRequest(
                    dates =
                        dates
                            .mapIndexed { index, (startDate, endDate, time) ->
                                index + 1 to
                                    ScheduleUnavailabilityDateRequest(
                                        startDate = startDate,
                                        endDate = endDate,
                                        periods = mutableMapOf(1 to period(time.first, time.second)),
                                    )
                            }.toMap()
                            .toMutableMap(),
                ),
        )
}
