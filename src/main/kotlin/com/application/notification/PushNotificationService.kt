package com.application.notification

import com.application.common.util.logger
import com.application.repository.PushSubscriptionRepository
import com.interaso.webpush.WebPush
import com.interaso.webpush.WebPushService
import org.springframework.stereotype.Service
import tools.jackson.databind.json.JsonMapper

@Service
class PushNotificationService(
    private val webPushService: WebPushService,
    private val pushSubscriptionRepository: PushSubscriptionRepository,
) {
    private val log = logger()
    private val mapper = JsonMapper()

    fun send(
        id: String,
        title: String,
        body: String,
        url: String,
    ) {
        val payload = mapper.writeValueAsString(mapOf("title" to title, "body" to body, "url" to url))

        pushSubscriptionRepository.findById(id).map {
            try {
                val state =
                    webPushService.send(
                        payload = payload,
                        endpoint = it.endpoint,
                        p256dh = it.p256dh,
                        auth = it.auth,
                    )

                if (state == WebPush.SubscriptionState.EXPIRED) {
                    log.warn("Subscription state expired user=${it.id}")
                    pushSubscriptionRepository.deleteById(it.id)
                }
            } catch (ex: Exception) {
                log.error("Subscription state exception user=${it.id}", ex)
            }
        }
    }
}
