package com.application.controller.dto.response

import com.application.domain.entity.Services

data class ServiceListResponse(
    val items: List<ServiceResponse>,
) {
    companion object {
        fun from(services: List<Services>) = ServiceListResponse(services.map { ServiceResponse.from(it) })
    }
}
