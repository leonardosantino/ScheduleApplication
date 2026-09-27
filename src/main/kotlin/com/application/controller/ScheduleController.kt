package com.application.controller

import com.application.common.util.Jwt
import com.application.common.util.logger
import com.application.controller.dto.request.ScheduleRequest
import com.application.controller.dto.response.ScheduleResponse
import com.application.service.ScheduleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/schedule")
class ScheduleController(
    private val scheduleService: ScheduleService,
) {
    private val log = logger()

    @PostMapping
    fun save(
        @RequestHeader authorization: String,
        @RequestBody request: ScheduleRequest,
    ) = scheduleService.save(request).let { ScheduleResponse.from(it) }.also {
        log.info("Saving schedule=${it.id} auth=${Jwt.sub(authorization)}")
    }

    @GetMapping("/provider/{id}")
    fun findByProviderId(
        @PathVariable id: String,
    ) = scheduleService.findByProviderId(id).let { ScheduleResponse.from(it) }
}
