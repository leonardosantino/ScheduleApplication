package com.application.controller.dto.response

import com.application.domain.entity.Provider

data class ProviderResponse(
    var id: String?,
    var slug: String,
    var name: String,
    var description: String,
    var category: String,
    var phone: String,
) {
    companion object {
        fun from(provider: Provider) =
            ProviderResponse(
                id = provider.id,
                slug = provider.slug,
                name = provider.name,
                description = provider.description,
                category = provider.category,
                phone = provider.phone,
            )
    }
}
