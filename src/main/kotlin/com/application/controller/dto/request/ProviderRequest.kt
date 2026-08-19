package com.application.controller.dto.request

import com.application.domain.entity.Provider
import com.application.domain.objects.UserStatus
import java.text.Normalizer
import java.text.Normalizer.normalize
import java.time.Instant

data class ProviderRequest(
    var id: String,
    var name: String,
    var role: String,
    var description: String,
    var phone: String,
) {
    fun toCreate(): Provider =
        Provider(
            id = id,
            slug =
                name.lowercase().let {
                    normalize(it, Normalizer.Form.NFD)
                        .replace(Regex("\\p{InCombiningDiacriticalMarks}+"), "")
                        .replace(" ", "")
                },
            name = name,
            role = role,
            description = description,
            phone = phone,
            status = UserStatus.ENABLED.value,
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
        )

    fun toUpdate(provider: Provider): Provider {
        provider.name = name
        provider.role = role
        provider.description = description
        provider.phone = phone

        provider.updatedAt = Instant.now()

        return provider
    }
}
