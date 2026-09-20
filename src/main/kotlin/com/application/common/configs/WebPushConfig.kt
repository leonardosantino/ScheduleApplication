package com.application.common.configs

import com.application.common.properties.VapidProperties
import com.interaso.webpush.VapidKeys
import com.interaso.webpush.WebPushService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class WebPushConfig(
    private val vapidProperties: VapidProperties,
) {
    @Bean
    fun webPushService(): WebPushService {
        val vapidKeys =
            VapidKeys.fromUncompressedBytes(
                vapidProperties.publicKey,
                vapidProperties.privateKey,
            )

        return WebPushService(
            subject = vapidProperties.subject,
            vapidKeys = vapidKeys,
        )
    }
}
