package com.application.service

import com.application.common.constants.ExMessage
import com.application.controller.dto.request.ScheduleRequest
import com.application.domain.entity.Schedule
import com.application.exception.NotFoundException
import com.application.repository.ScheduleRepository
import org.springframework.stereotype.Service

@Service
class ScheduleService(
    private val scheduleRepository: ScheduleRepository,
) {
    fun save(request: ScheduleRequest): Schedule =
        scheduleRepository
            .findById(request.id)
            .map {
                scheduleRepository.save(request.toUpdateSchedule(it))
            }.orElseGet {
                scheduleRepository.save(request.toCreateSchedule())
            }

    fun findByProviderId(id: String) = scheduleRepository.findById(id).orElseThrow { NotFoundException(ExMessage.SCHEDULE_NOT_FOUND) }
}
