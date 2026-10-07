package com.application.service

import com.application.common.constants.ExMessage
import com.application.common.constants.Zone
import com.application.controller.dto.request.ScheduleRequest
import com.application.domain.entity.Schedule
import com.application.domain.objects.ScheduleAvailability
import com.application.exception.NotFoundException
import com.application.repository.ScheduleRepository
import com.application.util.ScheduleAvailabilityUtil
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalTime

@Service
class ScheduleService(
    private val scheduleRepository: ScheduleRepository,
    private val appointmentService: AppointmentService,
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

    fun findAvailability(
        id: String,
        date: LocalDate,
        duration: Int,
    ): List<ScheduleAvailability> {
        val now = LocalTime.now(Zone.ID_AMERICA_SAO_PAULO)

        val schedule = findByProviderId(id)
        val booked = appointmentService.findAllByProviderIdAndDateAndStatusConfirmed(id, date).flatMap { it.times }.toSet()

        val minTime = if (date == LocalDate.now(Zone.ID_AMERICA_SAO_PAULO)) now.hour * 60 + now.minute else -1

        return ScheduleAvailabilityUtil.calculate(
            schedule = schedule,
            date = date,
            duration = duration,
            booked = booked,
            minTime = minTime,
        )
    }
}
