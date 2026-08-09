package com.application.domain.objects

enum class BookingStatus(
    val value: String,
) {
    CONFIRMED("Confirmado"),
    CANCELLED("Cancelado"),
}
