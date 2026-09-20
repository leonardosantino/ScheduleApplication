package com.application.notification.dto

import com.application.domain.entity.Appointment

data class AppointmentCreatedEvent(
    val appointment: Appointment,
)
