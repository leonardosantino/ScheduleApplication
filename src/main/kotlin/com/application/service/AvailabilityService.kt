package com.application.service

import com.application.controller.AvailabilityRequest
import com.application.domain.entity.Availability
import com.application.repository.AvailabilityDayRepository
import com.application.repository.AvailabilityPeriodRepository
import com.application.repository.AvailabilityRepository
import org.springframework.stereotype.Service

@Service
class AvailabilityService(
    private val availabilityRepository: AvailabilityRepository,
    private val availabilityDayRepository: AvailabilityDayRepository,
    private val availabilityPeriodRepository: AvailabilityPeriodRepository,
) {
    fun save(request: AvailabilityRequest): Availability {
        val availability = availabilityRepository.save(request.toAvailability())

        availability.days =
            request.days
                ?.map {
                    val day = availabilityDayRepository.save(it.toAvailabilityDay(availability.id as Long))

                    day.periods =
                        it.periods
                            ?.map { period ->
                                availabilityPeriodRepository.save(
                                    period.toAvailabilityPeriod(day.id as Long),
                                )
                            }?.toMutableList()

                    day
                }?.toMutableList()

        return availability
    }

    fun findById(id: Long) = availabilityRepository.findById(id)
}
