package com.application.service

import com.application.domain.entity.Services
import com.application.model.dto.request.ServiceRequest
import com.application.repository.ServicesRepository
import org.springframework.stereotype.Service

@Service
class ServicesService(
    private val servicesRepository: ServicesRepository,
) {
    fun save(request: ServiceRequest): Services = servicesRepository.save(request.toCreate())

    fun findAllByProviderId(id: String): List<Services> = servicesRepository.findAllByProviderId(id)
}
