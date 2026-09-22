package com.application.controller.dto.response

import com.application.domain.objects.AppointmentCancellation
import java.time.Instant

data class AppointmentCancellationResponse(
    var reason: String,
    var canceledBy: String,
    var canceledAt: Instant,
) {
    companion object {
        fun from(cancellation: AppointmentCancellation) =
            AppointmentCancellationResponse(
                reason = cancellation.reason,
                canceledBy = cancellation.canceledBy,
                canceledAt = cancellation.canceledAt,
            )
    }
}
