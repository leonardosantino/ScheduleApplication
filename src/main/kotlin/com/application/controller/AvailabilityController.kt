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
        @PathVariable id: String,
    ) = availabilityService.findById(id)
}

class AvailabilityRequest(
    var id: String?,
    var userId: String,
    var days: MutableList<AvailabilityDayRequest>?,
) {
    fun toAvailability() =
        Availability(
            id = id,
            userId = userId,
            days = days?.map { it.toAvailabilityDay() }?.toMutableList(),
        )
}

class AvailabilityDayRequest(
    var name: String,
    var periods: MutableList<AvailabilityPeriodRequest>?,
) {
    fun toAvailabilityDay() =
        AvailabilityDay(
            name = name,
            periods = periods?.map { it.toAvailabilityPeriod() }?.toMutableList(),
        )
}

class AvailabilityPeriodRequest(
    var start: Int,
    var end: Int,
) {
    fun toAvailabilityPeriod() =
        AvailabilityPeriod(
            start = start,
            end = end,
        )
}
