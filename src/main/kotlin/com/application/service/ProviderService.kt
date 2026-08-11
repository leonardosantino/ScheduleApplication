package com.application.service

import com.application.controller.dto.request.ProviderRequest
import com.application.repository.ProviderRepository
import org.springframework.stereotype.Service

@Service
class ProviderService(
    private val providerRepository: ProviderRepository,
) {
    fun update(request: ProviderRequest) = providerRepository.save(request.toCreate())

    fun findById(id: String) = providerRepository.findById(id)

    fun findBySlug(slug: String) = providerRepository.findBySlug(slug)
}
