package com.application.controller

import com.application.common.util.Jwt
import com.application.notification.dto.PushSubscriptionRequest
import com.application.service.PushSubscriptionService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/web")
class PushSubscriptionController(
    private val pushSubscriptionService: PushSubscriptionService,
) {
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun save(
        @RequestHeader authorization: String,
        @RequestBody request: PushSubscriptionRequest,
    ) = pushSubscriptionService.save(Jwt.sub(authorization), request)
}
