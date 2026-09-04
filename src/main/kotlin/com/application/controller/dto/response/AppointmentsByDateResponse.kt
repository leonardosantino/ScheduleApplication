package com.application.controller.dto.response

import com.application.domain.entity.Appointment

data class AppointmentsByDateResponse(
    val items: List<AppointmentByDateResponse>,
) {
    companion object {
        fun from(appointments: List<Appointment>) = AppointmentsByDateResponse(appointments.map { AppointmentByDateResponse.from(it) })
    }
}
