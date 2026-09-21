package com.application.domain.objects

import java.time.Instant

data class AppointmentCancellation(
    var reason: String,
    var canceledBy: String,
    var canceledAt: Instant,
)
