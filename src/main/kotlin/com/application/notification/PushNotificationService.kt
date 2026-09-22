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
        role: String?,
        title: String,
        body: String,
        url: String,
    ) {
        val payload = objectMapper.writeValueAsString(mapOf("title" to title, "body" to body, "url" to url))

        pushSubscriptionRepository.findByUserIdAndRole(id, role).map {
            try {
                val state =
                    webPushService.send(
                        payload = payload,
                        endpoint = it.endpoint,
                        p256dh = it.p256dh,
                        auth = it.auth,
                    )

                if (state == WebPush.SubscriptionState.EXPIRED) {
                    log.warn("push notification is expired user=${it.id} role=$role")
                    pushSubscriptionRepository.deleteById(it.id.toString())
                }
            } catch (ex: Exception) {
                log.error("push notification to user=${it.id} role=$role", ex)
            }
        }
    }
}
