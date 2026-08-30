package com.application.service

import com.application.common.constants.ExMessage
import com.application.controller.dto.request.ProviderRequest
import com.application.exception.NotFoundException
import com.application.repository.ProviderRepository
import org.springframework.stereotype.Service

@Service
class ProviderService(
    private val providerRepository: ProviderRepository,
) {
    fun save(request: ProviderRequest) = providerRepository.save(request.toCreate())

    fun update(request: ProviderRequest) =
        providerRepository
            .findById(request.id)
            .map { providerRepository.save(request.toUpdate(it)) }
            .orElseThrow { NotFoundException(ExMessage.PROVIDER_NOT_FOUND) }

    fun findById(id: String) = providerRepository.findById(id)

    fun findBySlug(slug: String) =
        providerRepository
            .findBySlug(slug)
            .orElseThrow { NotFoundException(ExMessage.ANNOUNCEMENT_NOT_FOUND) }
}
