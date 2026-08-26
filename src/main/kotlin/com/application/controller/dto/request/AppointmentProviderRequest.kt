package com.application.controller.dto.request

import com.application.domain.objects.AppointmentProvider

data class AppointmentProviderRequest(
    var id: String,
    var name: String,
    var phone: String,
) {
    fun toProvider() =
        AppointmentProvider(
            id = id,
            name = name,
            phone = phone,
        )
}
