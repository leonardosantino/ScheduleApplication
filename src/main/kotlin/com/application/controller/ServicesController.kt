package com.application.controller

import com.application.controller.dto.request.ServiceRequest
import com.application.controller.dto.response.ServiceResponse
import com.application.controller.dto.response.ServicesResponse
import com.application.security.UserAuth
import com.application.service.ServicesService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/services")
class ServicesController(
    private val servicesService: ServicesService,
) : UserAuth() {
    @PostMapping
    fun save(
        @RequestHeader authorization: String,
        @RequestBody request: ServiceRequest,
    ): ServiceResponse {
        authorize(request.provider.id, authorization)

        return servicesService.save(request).let { ServiceResponse.from(it) }
    }

    @PutMapping
    fun update(
        @RequestHeader authorization: String,
        @RequestBody request: ServiceRequest,
    ): ServiceResponse {
        authorize(request.provider.id, authorization)

        return servicesService.update(request).let { ServiceResponse.from(it) }
    }

    @GetMapping("/provider/{id}")
    fun findAllByProviderId(
        @PathVariable id: String,
    ) = servicesService
        .findAllByProviderId(id)
        .let { ServicesResponse.from(it) }

    @GetMapping("/provider/{id}/status/{status}")
    fun findAllByProviderIdAndStatus(
        @PathVariable id: String,
        @PathVariable status: String,
    ) = servicesService
        .findAllByProviderIdAndStatus(id, status)
        .let { ServicesResponse.from(it) }

    @DeleteMapping("/{id}")
    fun deleteById(
        @RequestHeader authorization: String,
        @PathVariable id: String,
    ) {
        authorize(servicesService.findById(id).provider.id, authorization)

        servicesService
            .deleteById(id)
    }
}
