package com.application.service

import com.application.domain.entity.Availability
import com.application.model.dto.request.AvailabilityRequest
import com.application.repository.AvailabilityRepository
import org.springframework.stereotype.Service

@Service
class AvailabilityService(
    private val availabilityRepository: AvailabilityRepository,
) {
    fun save(request: AvailabilityRequest): Availability = availabilityRepository.save(request.toAvailability())

    fun findById(id: String) = availabilityRepository.findById(id)
}
