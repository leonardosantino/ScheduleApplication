package com.application.service

import com.application.domain.entity.Booking
import com.application.model.dto.request.BookingRequest
import com.application.repository.BookingRepository
import org.springframework.stereotype.Service

@Service
class BookingService(
    private val bookingRepository: BookingRepository,
) {
    fun save(request: BookingRequest): Booking = bookingRepository.save(request.toCreate())

    fun findAllByProviderId(id: String): List<Booking> = bookingRepository.findAllByServiceProviderId(id)

    fun findAllByUserId(id: String): List<Booking> = bookingRepository.findAllByUserId(id)
}
