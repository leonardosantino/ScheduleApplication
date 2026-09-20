package com.application.notification.dto

import com.application.domain.entity.Appointment

data class AppointmentCanceledEvent(
    val appointment: Appointment,
)
