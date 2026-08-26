package com.application.controller.dto.response

import com.application.domain.objects.AppointmentProvider

data class AppointmentProviderResponse(
    var id: String,
    var name: String,
    var phone: String,
) {
    companion object {
        fun from(provider: AppointmentProvider) =
            AppointmentProviderResponse(
                id = provider.id,
                name = provider.name,
                phone = provider.phone,
            )
    }
}
