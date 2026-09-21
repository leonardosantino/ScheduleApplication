package com.application.notification.dto

import com.application.domain.entity.Appointment

data class AppointmentReminderEvent(
    val appointment: Appointment,
)
