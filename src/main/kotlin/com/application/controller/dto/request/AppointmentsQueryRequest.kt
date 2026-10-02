package com.application.controller.dto.request

import com.application.domain.objects.AppointmentScope

data class AppointmentsQueryRequest(
    val scope: AppointmentScope,
    val page: Int = 0,
    val size: Int = 10,
)
