package com.application.repository

import com.application.domain.entity.PushSubscription
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PushSubscriptionRepository : MongoRepository<PushSubscription, String>
