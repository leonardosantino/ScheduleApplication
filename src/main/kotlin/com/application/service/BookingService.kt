package com.application.service

import com.application.domain.entity.Booking
import com.application.domain.objects.BookingStatus
import com.application.repository.BookingRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.Instant

@Service
class BookingService(
    private val bookingRepository: BookingRepository,
) {
    fun save(request: BookingRequest) = bookingRepository.save(request.toCreate())

    fun findAllByProviderId(id: String) =
        bookingRepository
            .findAllByProviderId(id)
            .let { BookingResponse.toListResponse(it) }

    fun findAllByUserId(id: String) =
        bookingRepository
            .findAllByUserId(id)
            .let { BookingResponse.toListResponse(it) }
}

data class BookingResponse(
    val items: List<Booking>,
) {
    companion object {
        fun toListResponse(bookings: List<Booking>) = BookingResponse(bookings)
    }
}

data class BookingRequest(
    var id: String?,
    var userId: String,
    var providerId: String,
    var name: String,
    var description: String,
    var date: Instant,
    var time: Double,
    var value: BigDecimal,
    var status: String?,
    var createdAt: Instant?,
    var updatedAt: Instant?,
) {
    fun toCreate() =
        Booking(
            id = null,
            userId = userId,
            providerId = providerId,
            name = name,
            description = description,
            date = date,
            time = time,
            value = value,
            status = BookingStatus.CONFIRMED.value,
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
        )
}
