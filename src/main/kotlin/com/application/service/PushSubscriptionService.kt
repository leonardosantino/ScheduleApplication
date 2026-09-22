package com.application.service

import com.application.notification.dto.PushSubscriptionRequest
import com.application.repository.PushSubscriptionRepository
import org.springframework.stereotype.Service

@Service
class PushSubscriptionService(
    private val pushSubscriptionRepository: PushSubscriptionRepository,
) {
    fun save(
        id: String,
        request: PushSubscriptionRequest,
    ) {
        val subscription =
            pushSubscriptionRepository
                .findByUserIdAndRole(id, request.role)
                .map { request.toUpdate(it) }
                .orElseGet { request.toCreate(id) }

        pushSubscriptionRepository.save(subscription)
    }
}
