package com.application.domain.objects

enum class AppointmentStatus(
    val value: String,
) {
    PENDING("Pendente"),
    CONFIRMED("Confirmado"),
    CANCELED("Cancelado"),
}
