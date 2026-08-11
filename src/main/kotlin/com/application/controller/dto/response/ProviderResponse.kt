package com.application.controller.dto.response

import com.application.domain.entity.Provider
import java.time.Instant

data class ProviderResponse(
    var id: String?,
    var slug: String,
    var name: String,
    var description: String,
    var role: String,
    var status: String,
    var createdAt: Instant,
    var updatedAt: Instant,
) {
    companion object {
        fun from(provider: Provider) =
            ProviderResponse(
                id = provider.id,
                slug = provider.slug,
                name = provider.name,
                description = provider.description,
                role = provider.role,
                status = provider.status,
                createdAt = provider.createdAt,
                updatedAt = provider.updatedAt,
            )
    }
}
