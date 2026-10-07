package com.application.controller

import com.application.controller.dto.request.ScheduleRequest
import com.application.controller.dto.response.ScheduleAvailabilityResponse
import com.application.controller.dto.response.ScheduleResponse
import com.application.security.UserAuth
import com.application.service.ScheduleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/schedule")
class ScheduleController(
    private val scheduleService: ScheduleService,
) : UserAuth() {
    @PostMapping
    fun save(
        @RequestHeader authorization: String,
        @RequestBody request: ScheduleRequest,
    ): ScheduleResponse {
        authorize(request.id, authorization)

        return scheduleService.save(request).let { ScheduleResponse.from(it) }
    }

    @GetMapping("/provider/{id}")
    fun findByProviderId(
        @PathVariable id: String,
    ) = scheduleService
        .findByProviderId(id)
        .let { ScheduleResponse.from(it) }

    @GetMapping("/provider/{id}/date/{date}/availability")
    fun findAvailability(
        @PathVariable id: String,
        @PathVariable date: LocalDate,
        @RequestParam duration: Int,
    ) = scheduleService.findAvailability(id, date, duration).let { ScheduleAvailabilityResponse.from(it) }
}
