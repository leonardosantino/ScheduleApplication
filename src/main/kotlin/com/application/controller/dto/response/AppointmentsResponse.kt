package com.application.controller.dto.response

import com.application.domain.entity.Appointment

data class AppointmentsResponse(
    val items: List<AppointmentResponse>,
) {
    companion object {
        fun from(appointments: List<Appointment>) = AppointmentsResponse(appointments.map { AppointmentResponse.from(it) })
    }
}
