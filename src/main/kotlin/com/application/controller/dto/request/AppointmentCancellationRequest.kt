package com.application.controller.dto.request

import com.application.domain.entity.Appointment
import com.application.domain.objects.AppointmentCancellation
import com.application.domain.objects.AppointmentStatus
import java.time.Instant

data class AppointmentCancellationRequest(
    var cancellation: CancellationRequest,
) {
    fun toUpdate(appointment: Appointment): Appointment {
        appointment.status = AppointmentStatus.CANCELED.value
        appointment.cancellation = cancellation.toCancellation()
        appointment.updatedAt = Instant.now()

        return appointment
    }
}

data class CancellationRequest(
    var reason: String,
    var canceledBy: String,
) {
    fun toCancellation(): AppointmentCancellation =
        AppointmentCancellation(
            reason = reason,
            canceledBy = canceledBy,
            canceledAt = Instant.now(),
        )
}
