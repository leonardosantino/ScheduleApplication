package com.application.controller

import com.application.domain.entity.Availability
import com.application.domain.entity.AvailabilityDay
import com.application.domain.entity.AvailabilityPeriod
import com.application.service.AvailabilityService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/availabilities")
class AvailabilityController(
    private val availabilityService: AvailabilityService,
) {
    @PostMapping
    fun save(
        @RequestBody request: AvailabilityRequest,
    ) = availabilityService.save(request)

    @GetMapping("/{id}")
    fun findById(
        @PathVariable id: Long,
    ) = availabilityService.findById(id)
}

class AvailabilityRequest(
    var id: Long?,
    var userId: String,
    var days: MutableList<AvailabilityDayRequest>?,
) {
    fun toAvailability() =
        Availability(
            id = id,
            userId = userId,
            days = null,
        )
}

class AvailabilityDayRequest(
    var id: Long?,
    var name: String,
    var periods: MutableList<AvailabilityPeriodRequest>?,
) {
    fun toAvailabilityDay(availabilityId: Long) =
        AvailabilityDay(
            id = id,
            availabilityId = availabilityId,
            name = name,
            periods = null,
        )
}

class AvailabilityPeriodRequest(
    var id: Long?,
    var start: Int,
    var end: Int,
) {
    fun toAvailabilityPeriod(availabilityDayId: Long) =
        AvailabilityPeriod(
            id = id,
            availabilityDayId = availabilityDayId,
            start = start,
            end = end,
        )
}
