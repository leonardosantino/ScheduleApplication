package com.application.controller.dto.response

import com.application.domain.objects.BookingProvider

data class BookingProviderResponse(
    var id: String,
    var name: String,
    var phone: String,
) {
    companion object {
        fun from(provider: BookingProvider) =
            BookingProviderResponse(
                id = provider.id,
                name = provider.name,
                phone = provider.phone,
            )
    }
}
