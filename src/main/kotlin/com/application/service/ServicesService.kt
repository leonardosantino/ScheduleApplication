package com.application.service

import com.application.common.constants.ExMessage
import com.application.controller.dto.request.ServiceRequest
import com.application.domain.entity.Services
import com.application.exception.NotFoundException
import com.application.repository.ServicesRepository
import org.springframework.stereotype.Service

@Service
class ServicesService(
    private val servicesRepository: ServicesRepository,
) {
    fun save(request: ServiceRequest): Services = servicesRepository.save(request.toCreate())

    fun update(request: ServiceRequest): Services {
        val service =
            servicesRepository
                .findById(request.id.orEmpty())
                .orElseThrow { NotFoundException(ExMessage.SERVICE_NOT_FOUND) }

        return servicesRepository.save(request.toUpdate(service))
    }

    fun findAllByProviderId(id: String): List<Services> = servicesRepository.findAllByProviderId(id)

    fun findAllByProviderIdAndStatus(
        id: String,
        status: String,
    ): List<Services> = servicesRepository.findAllByProviderIdAndStatus(id, status)

    fun deleteById(id: String) = servicesRepository.deleteById(id)
}
