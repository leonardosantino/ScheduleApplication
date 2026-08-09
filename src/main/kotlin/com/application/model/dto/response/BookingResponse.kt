package com.application.model.dto.response

import com.application.domain.entity.Booking
import com.application.domain.entity.BookingUser
import java.math.BigDecimal
import java.time.Instant
import com.application.domain.entity.BookingService as BookingServiceEntity

data class BookingResponse(
    var id: String?,
    var user: BookingUserResponse,
    var service: BookingServiceResponse,
    var date: Instant,
    var time: Double,
    var createdAt: Instant,
    var updatedAt: Instant,
) {
    companion object {
        fun from(booking: Booking) =
            BookingResponse(
                id = booking.id,
                user = BookingUserResponse.from(booking.user),
                service = BookingServiceResponse.from(booking.service),
                date = booking.date,
                time = booking.time,
                createdAt = booking.createdAt,
                updatedAt = booking.updatedAt,
            )
    }
}

data class BookingUserResponse(
    var id: String,
    var name: String,
    var lastName: String,
    var phone: String?,
) {
    companion object {
        fun from(user: BookingUser) =
            BookingUserResponse(
                id = user.id,
                name = user.name,
                lastName = user.lastName,
                phone = user.phone,
            )
    }
}

data class BookingServiceResponse(
    var id: String,
    var providerId: String,
    var name: String,
    var description: String,
    var time: Long,
    var value: BigDecimal,
) {
    companion object {
        fun from(service: BookingServiceEntity) =
            BookingServiceResponse(
                id = service.id,
                providerId = service.providerId,
                name = service.name,
                description = service.description,
                time = service.time,
                value = service.value,
            )
    }
}

data class BookingListResponse(
    val items: List<BookingResponse>,
) {
    companion object {
        fun from(bookings: List<Booking>) = BookingListResponse(bookings.map { BookingResponse.from(it) })
    }
}
