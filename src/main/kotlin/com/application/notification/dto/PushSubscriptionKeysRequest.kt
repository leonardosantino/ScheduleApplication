package com.application.notification.dto

data class PushSubscriptionKeysRequest(
    var p256dh: String,
    var auth: String,
)
