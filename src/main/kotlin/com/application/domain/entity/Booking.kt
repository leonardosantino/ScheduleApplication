package com.application.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.Instant

@Document(collection = "bookings")
data class Booking(
    @Id
    var id: String? = null,
    var userId: String,
    var providerId: String,
    var name: String,
    var description: String,
    var date: Instant,
    var time: Double,
    var value: BigDecimal,
    var status: String,
    var createdAt: Instant? = null,
    var updatedAt: Instant? = null,
)
