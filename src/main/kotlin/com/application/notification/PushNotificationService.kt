package com.application.notification

import com.application.repository.PushSubscriptionRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.interaso.webpush.WebPush
import com.interaso.webpush.WebPushService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class PushNotificationService(
    private val webPushService: WebPushService,
    private val pushSubscriptionRepository: PushSubscriptionRepository,
) {
    private val log = LoggerFactory.getLogger(javaClass)
    private val objectMapper = ObjectMapper()

    fun send(
        id: String,
        title: String,
        body: String,
        url: String,
    ) {
        val payload = objectMapper.writeValueAsString(mapOf("title" to title, "body" to body, "url" to url))

        pushSubscriptionRepository.findAllById(id).forEach { subscription ->
            try {
                val state =
                    webPushService.send(
                        payload = payload,
                        endpoint = subscription.endpoint,
                        p256dh = subscription.p256dh,
                        auth = subscription.auth,
                    )

                if (state == WebPush.SubscriptionState.EXPIRED) {
                    pushSubscriptionRepository.deleteByEndpoint(subscription.endpoint)
                }
            } catch (ex: Exception) {
                log.error("push notification to endpoint ${subscription.endpoint}", ex)
            }
        }
    }
}
