package com.application.controller

import com.application.controller.dto.request.ProviderRequest
import com.application.controller.dto.response.ProviderResponse
import com.application.service.ProviderService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/providers")
class ProviderController(
    private val providerService: ProviderService,
) {
    @PostMapping
    fun save(
        @RequestBody request: ProviderRequest,
    ) = providerService.save(request).let { ProviderResponse.from(it) }

    @PutMapping
    fun update(
        @RequestBody request: ProviderRequest,
    ) = providerService.update(request).let { ProviderResponse.from(it) }

    @GetMapping("/{id}")
    fun findById(
        @PathVariable id: String,
    ) = providerService.findById(id).map { ProviderResponse.from(it) }

    @GetMapping("/slug/{slug}")
    fun findBySlug(
        @PathVariable slug: String,
    ) = providerService.findBySlug(slug).let { ProviderResponse.from(it) }
}
