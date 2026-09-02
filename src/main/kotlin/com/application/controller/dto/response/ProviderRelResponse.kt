package com.application.controller.dto.response

import com.application.domain.entity.RelCustomerProvider

data class ProviderRelResponse(
    var name: String,
    var slug: String,
    var description: String,
    var category: String,
) {
    companion object {
        fun from(rel: RelCustomerProvider) =
            ProviderRelResponse(
                name = rel.provider.name,
                slug = rel.provider.slug,
                description = rel.provider.description,
                category = rel.provider.category,
            )
    }
}
