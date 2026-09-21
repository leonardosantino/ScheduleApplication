package com.application.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document
data class PushSubscription(
    @Id
    var id: String,
    var endpoint: String,
    var p256dh: String,
    var auth: String,
    var createdAt: Instant,
)
