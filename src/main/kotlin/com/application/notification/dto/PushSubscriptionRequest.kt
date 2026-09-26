package com.application.notification.dto

import com.application.domain.entity.PushSubscription
import java.time.Instant

data class PushSubscriptionRequest(
    var id: String,
    var endpoint: String,
    var keys: PushSubscriptionKeysRequest,
) {
    fun toCreate() =
        PushSubscription(
            id = id,
            endpoint = endpoint,
            p256dh = keys.p256dh,
            auth = keys.auth,
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
        )

    fun toUpdate(subscription: PushSubscription): PushSubscription {
        subscription.endpoint = endpoint
        subscription.p256dh = keys.p256dh
        subscription.auth = keys.auth

        subscription.updatedAt = Instant.now()

        return subscription
    }
}
