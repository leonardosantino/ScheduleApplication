package com.application.controller.dto.request

import com.application.domain.objects.BookingProvider

data class BookingProviderRequest(
    var id: String,
    var name: String,
) {
    fun toProvider() =
        BookingProvider(
            id = id,
            name = name,
        )
}
