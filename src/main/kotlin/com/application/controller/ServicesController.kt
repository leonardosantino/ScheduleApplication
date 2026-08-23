package com.application.controller

import com.application.controller.dto.request.ServiceRequest
import com.application.controller.dto.response.ServiceListResponse
import com.application.controller.dto.response.ServiceResponse
import com.application.service.ServicesService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/services")
class ServicesController(
    private val servicesService: ServicesService,
) {
    @PostMapping
    fun save(
        @RequestBody user: ServiceRequest,
    ) = servicesService.save(user).let { ServiceResponse.from(it) }

    @PutMapping
    fun update(
        @RequestBody request: ServiceRequest,
    ) = servicesService.update(request).let { ServiceResponse.from(it) }

    @GetMapping("/provider/{id}")
    fun findAllByProviderId(
        @PathVariable id: String,
    ) = servicesService.findAllByProviderId(id).let { ServiceListResponse.from(it) }

    @GetMapping("/provider/{id}/status/{status}")
    fun findAllByProviderIdAndStatus(
        @PathVariable id: String,
        @PathVariable status: String,
    ) = servicesService.findAllByProviderIdAndStatus(id, status).let { ServiceListResponse.from(it) }

    @DeleteMapping("/{id}")
    fun deleteById(
        @PathVariable id: String,
    ) = servicesService.deleteById(id)
}
