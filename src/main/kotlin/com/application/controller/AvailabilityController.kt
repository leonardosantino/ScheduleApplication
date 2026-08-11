package com.application.controller

import com.application.controller.dto.request.AvailabilityRequest
import com.application.controller.dto.response.AvailabilityResponse
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
    ) = availabilityService.save(request).let { AvailabilityResponse.from(it) }

    @GetMapping("/{id}")
    fun findById(
        @PathVariable id: String,
    ) = availabilityService.findById(id).map { AvailabilityResponse.from(it) }
}
