package com.application.controller.dto.response

import com.application.domain.entity.RelCustomerProvider

data class ProviderRelResponse(
    var id: String,
    var name: String,
    var slug: String,
    var description: String,
    var category: String,
    var phone: String,
) {
    companion object {
        fun from(rel: RelCustomerProvider) =
            ProviderRelResponse(
                id = rel.id.provider,
                name = rel.provider.name,
                slug = rel.provider.slug,
                description = rel.provider.description,
                category = rel.provider.category,
                phone = rel.provider.phone,
            )
    }
}
