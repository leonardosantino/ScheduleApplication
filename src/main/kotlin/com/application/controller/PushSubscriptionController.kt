package com.application.controller

import com.application.notification.dto.PushSubscriptionRequest
import com.application.security.UserAuth
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
) : UserAuth() {
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun save(
        @RequestHeader authorization: String,
        @RequestBody request: PushSubscriptionRequest,
    ) {
        authorize(request.id, authorization)

        return pushSubscriptionService.save(request)
    }
}
