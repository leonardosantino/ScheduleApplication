package com.application.service

import com.application.common.constants.ExMessage
import com.application.controller.dto.request.AvailabilityRequest
import com.application.domain.entity.Availability
import com.application.exception.NotFoundException
import com.application.repository.AvailabilityRepository
import org.springframework.stereotype.Service

@Service
class AvailabilityService(
    private val availabilityRepository: AvailabilityRepository,
) {
    fun save(request: AvailabilityRequest): Availability = availabilityRepository.save(request.toAvailability())

    fun findByProviderId(id: String) =
        availabilityRepository.findById(id).orElseThrow { NotFoundException(ExMessage.AVAILABILITY_NOT_FOUND) }
}
