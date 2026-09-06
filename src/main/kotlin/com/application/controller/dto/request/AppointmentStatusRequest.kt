package com.application.controller.dto.request

import com.application.domain.entity.Appointment
import java.time.Instant

data class AppointmentStatusRequest(
    var id: String,
    var status: String,
) {
    fun toUpdate(appointment: Appointment): Appointment {
        appointment.status = status
        appointment.updatedAt = Instant.now()

        return appointment
    }
}
