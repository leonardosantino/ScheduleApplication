package com.application.controller

import com.application.common.util.Jwt
import com.application.controller.dto.request.ProviderRequest
import com.application.controller.dto.response.ProviderResponse
import com.application.controller.dto.response.RelProvidersResponse
import com.application.service.ProviderService
import com.application.service.RelCustomerProviderService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/providers")
class ProviderController(
    private val providerService: ProviderService,
    private val relCustomerProviderService: RelCustomerProviderService,
) {
    @PostMapping
    fun save(
        @RequestBody request: ProviderRequest,
        @RequestHeader authorization: String,
    ) = providerService.save(request.copy(id = Jwt.sub(authorization))).let { ProviderResponse.from(it) }

    @PutMapping
    fun update(
        @RequestBody request: ProviderRequest,
        @RequestHeader authorization: String,
    ) = providerService.update(request.copy(id = Jwt.sub(authorization))).let { ProviderResponse.from(it) }

    @GetMapping("/{id}")
    fun findById(
        @PathVariable id: String,
    ) = providerService.findById(id).map { ProviderResponse.from(it) }

    @GetMapping("/slug/{slug}")
    fun findBySlug(
        @PathVariable slug: String,
    ) = providerService.findBySlug(slug).let { ProviderResponse.from(it) }

    @GetMapping("/customer/{id}")
    fun findAllByCustomerId(
        @PathVariable id: String,
    ) = relCustomerProviderService.findAllByCustomerId(id).let { RelProvidersResponse.from(it) }
}
