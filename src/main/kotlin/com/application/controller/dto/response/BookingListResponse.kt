package com.application.controller.dto.response

import com.application.domain.entity.Booking

data class BookingListResponse(
    val items: List<BookingResponse>,
) {
    companion object {
        fun from(bookings: List<Booking>) = BookingListResponse(bookings.map { BookingResponse.from(it) })
    }
}
