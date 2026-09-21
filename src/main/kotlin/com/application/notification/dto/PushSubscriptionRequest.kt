package com.application.notification.dto

import com.application.domain.entity.PushSubscription
import java.time.Instant

data class PushSubscriptionRequest(
    var endpoint: String,
    var keys: PushSubscriptionKeysRequest,
) {
    fun toCreate(id: String) =
        PushSubscription(
            id = id,
            endpoint = endpoint,
            p256dh = keys.p256dh,
            auth = keys.auth,
            createdAt = Instant.now(),
        )
}
