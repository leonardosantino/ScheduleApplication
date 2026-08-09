package com.application.service

import com.application.domain.entity.Booking
import com.application.domain.entity.BookingUser
import com.application.repository.BookingRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.Instant
import com.application.domain.entity.BookingService as BookingServiceEntity

@Service
class BookingService(
    private val bookingRepository: BookingRepository,
) {
    fun save(request: BookingRequest) = bookingRepository.save(request.toCreate())

    fun findAllByProviderId(id: String) =
        bookingRepository
            .findAllByServiceProviderId(id)
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
    var user: BookingUserRequest,
    var service: BookingServiceRequest,
    var date: Instant,
    var time: Double,
) {
    fun toCreate() =
        Booking(
            id = null,
            user = user.toBookingUser(),
            service = service.toBookingService(),
            date = date,
            time = time,
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
        )
}

data class BookingUserRequest(
    var id: String,
    var name: String,
    var lastName: String,
    var phone: String?,
) {
    fun toBookingUser() =
        BookingUser(
            id = id,
            name = name,
            lastName = lastName,
            phone = phone,
        )
}

data class BookingServiceRequest(
    var id: String,
    var providerId: String,
    var name: String,
    var description: String,
    var time: Long,
    var value: BigDecimal,
) {
    fun toBookingService() =
        BookingServiceEntity(
            id = id,
            providerId = providerId,
            name = name,
            description = description,
            time = time,
            value = value,
        )
}
