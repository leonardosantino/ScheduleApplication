package com.application.controller.dto.request

import com.application.domain.objects.BookingUser

data class BookingUserRequest(
    var id: String,
    var name: String,
    var lastName: String,
    var phone: String?,
) {
    fun toBookingUser() =
        BookingUser(
            id = id,
            name = name,
            lastName = lastName,
            phone = phone,
        )
}
