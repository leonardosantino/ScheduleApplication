package com.application.notification

import com.application.notification.dto.PushSubscriptionRequest
import org.springframework.stereotype.Service

@Service
class PushSubscriptionService(
    private val pushSubscriptionRepository: PushSubscriptionRepository,
) {
    fun save(
        userId: String,
        request: PushSubscriptionRequest,
    ) {
        pushSubscriptionRepository.findByIdAndEndpoint(userId, request.endpoint)?.let { return }

        pushSubscriptionRepository.save(request.toCreate(userId))
    }
}
