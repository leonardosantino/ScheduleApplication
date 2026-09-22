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
        pushSubscriptionRepository.findByUserIdAndRole(id, request.role).orElseGet {
            pushSubscriptionRepository.save(request.toCreate(id))
        }
    }
}
