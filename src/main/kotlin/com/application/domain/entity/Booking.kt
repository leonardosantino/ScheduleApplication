package com.application.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.Instant

@Document(collection = "bookings")
data class Booking(
    @Id
    var id: String?,
    var user: BookingUser,
    var provider: BookingProvider,
    var service: BookingService,
    var date: Instant,
    var times: List<Double>,
    var createdAt: Instant,
    var updatedAt: Instant,
)

class BookingService(
    var id: String,
    var name: String,
    var description: String,
    var time: Long,
    var value: BigDecimal,
)

class BookingUser(
    var id: String,
    var name: String,
    var lastName: String,
    var phone: String?,
)

class BookingProvider(
    var id: String,
    var name: String,
)
