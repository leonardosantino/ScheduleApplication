package com.application.controller.dto.response

import com.application.domain.entity.Booking
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

data class BookingResponse(
    var id: String?,
    var user: BookingUserResponse,
    var provider: BookingProviderResponse,
    var service: BookingServiceResponse,
    var date: LocalDate,
    var times: List<Int>,
    var createdAt: Instant,
    var updatedAt: Instant,
) {
    companion object {
        fun from(booking: Booking) =
            BookingResponse(
                id = booking.id,
                user = BookingUserResponse.from(booking.user),
                provider = BookingProviderResponse.from(booking.provider),
                service = BookingServiceResponse.from(booking.service),
                date = LocalDate.ofInstant(booking.date, ZoneOffset.UTC),
                times = booking.times,
                createdAt = booking.createdAt,
                updatedAt = booking.updatedAt,
            )
    }
}
