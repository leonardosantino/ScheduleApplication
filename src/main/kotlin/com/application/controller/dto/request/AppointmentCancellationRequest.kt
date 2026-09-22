package com.application.controller.dto.request

import com.application.domain.entity.Appointment
import com.application.domain.objects.AppointmentCancellation
import com.application.domain.objects.AppointmentStatus
import java.time.Instant

data class AppointmentCancellationRequest(
    var reason: String,
    var canceledBy: String,
) {
    fun toUpdate(appointment: Appointment): Appointment {
        appointment.status = AppointmentStatus.CANCELED.value
        appointment.cancellation =
            AppointmentCancellation(
                reason = reason,
                canceledBy = canceledBy,
                canceledAt = Instant.now(),
            )
        appointment.updatedAt = Instant.now()

        return appointment
    }
}
