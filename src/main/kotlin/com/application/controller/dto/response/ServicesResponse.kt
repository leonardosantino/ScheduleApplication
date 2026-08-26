package com.application.controller.dto.response

import com.application.domain.entity.Services

data class ServicesResponse(
    val items: List<ServiceResponse>,
) {
    companion object {
        fun from(services: List<Services>) = ServicesResponse(services.map { ServiceResponse.from(it) })
    }
}
