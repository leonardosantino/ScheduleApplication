package com.application.notification

import com.application.notification.entity.PushSubscription
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PushSubscriptionRepository : MongoRepository<PushSubscription, String> {
    fun findAllById(id: String): List<PushSubscription>

    fun findByIdAndEndpoint(
        id: String,
        endpoint: String,
    ): PushSubscription?

    fun deleteByEndpoint(endpoint: String)
}
