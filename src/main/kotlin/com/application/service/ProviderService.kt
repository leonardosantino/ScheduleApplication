package com.application.service

import com.application.common.constants.ExMessage
import com.application.controller.dto.request.ProviderRequest
import com.application.domain.entity.Provider
import com.application.domain.objects.UserStatus
import com.application.exception.NotFoundException
import com.application.repository.ProviderRepository
import org.springframework.stereotype.Service

@Service
class ProviderService(
    private val providerRepository: ProviderRepository,
) {
    fun save(request: ProviderRequest): Provider = providerRepository.save(request.toCreate())

    fun update(request: ProviderRequest): Provider =
        providerRepository
            .findById(request.id)
            .map { providerRepository.save(request.toUpdate(it)) }
            .orElseThrow { NotFoundException(ExMessage.PROVIDER_NOT_FOUND) }

    fun findById(id: String): Provider = providerRepository.findById(id).orElseThrow { NotFoundException(ExMessage.PROVIDER_NOT_FOUND) }

    fun findBySlug(slug: String): Provider =
        providerRepository
            .findBySlugAndStatus(slug, UserStatus.ENABLED.value)
            .orElseThrow { NotFoundException(ExMessage.ANNOUNCEMENT_NOT_FOUND) }
}
