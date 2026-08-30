package com.application.controller.dto.response

import com.application.domain.entity.RelCustomerProvider

data class RelProvidersResponse(
    val items: List<ProviderRelResponse>,
) {
    companion object {
        fun from(relations: List<RelCustomerProvider>) = RelProvidersResponse(relations.map { ProviderRelResponse.from(it) })
    }
}
