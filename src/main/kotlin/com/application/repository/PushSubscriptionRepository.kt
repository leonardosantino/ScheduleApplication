package com.application.repository

import com.application.domain.entity.PushSubscription
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface PushSubscriptionRepository : MongoRepository<PushSubscription, String> {
    fun findByUserIdAndRole(
        id: String,
        role: String?,
    ): Optional<PushSubscription>
}
