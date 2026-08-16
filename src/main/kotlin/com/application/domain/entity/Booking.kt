package com.application.domain.entity

import com.application.domain.objects.BookingProvider
import com.application.domain.objects.BookingService
import com.application.domain.objects.BookingUser
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "bookings")
data class Booking(
    @Id
    var id: String?,
    var user: BookingUser,
    var provider: BookingProvider,
    var service: BookingService,
    var date: Instant,
    var times: List<Int>,
    var createdAt: Instant,
    var updatedAt: Instant,
)
