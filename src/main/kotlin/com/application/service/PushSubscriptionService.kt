package com.application.service

import com.application.notification.dto.PushSubscriptionRequest
import com.application.repository.PushSubscriptionRepository
import org.springframework.stereotype.Service

@Service
class PushSubscriptionService(
    private val pushSubscriptionRepository: PushSubscriptionRepository,
) {
    fun save(request: PushSubscriptionRequest) {
        val subscription =
            pushSubscriptionRepository
                .findById(request.id)
                .map { request.toUpdate(it) }
                .orElseGet { request.toCreate() }

        pushSubscriptionRepository.save(subscription)
    }
}
