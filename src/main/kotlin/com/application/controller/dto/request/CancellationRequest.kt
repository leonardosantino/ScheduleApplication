package com.application.controller.dto.request

import com.application.domain.objects.AppointmentCancellation
import java.time.Instant

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
