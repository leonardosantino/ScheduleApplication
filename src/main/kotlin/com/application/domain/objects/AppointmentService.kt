package com.application.domain.objects

import java.math.BigDecimal

class AppointmentService(
    var id: String,
    var name: String,
    var description: String,
    var time: Long,
    var value: BigDecimal,
)
