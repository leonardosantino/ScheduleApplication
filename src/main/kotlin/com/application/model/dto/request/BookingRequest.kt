package com.application.model.dto.request

import com.application.domain.entity.Booking
import com.application.domain.entity.BookingProvider
import com.application.domain.entity.BookingUser
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import com.application.domain.entity.BookingService as BookingServiceEntity

data class BookingRequest(
    var id: String?,
    var user: BookingUserRequest,
    var provider: BookingProviderRequest,
    var service: BookingServiceRequest,
    var date: LocalDate,
    var time: Double,
) {
    fun toCreate() =
        Booking(
            id = null,
            user = user.toBookingUser(),
            provider = provider.toProvider(),
            service = service.toBookingService(),
            date = date.atStartOfDay(ZoneOffset.UTC).toInstant(),
            time = time,
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
        )
}

data class BookingProviderRequest(
    var id: String,
    var name: String,
) {
    fun toProvider() =
        BookingProvider(
            id = id,
            name = name,
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
    var name: String,
    var description: String,
    var time: Long,
    var value: BigDecimal,
) {
    fun toBookingService() =
        BookingServiceEntity(
            id = id,
            name = name,
            description = description,
            time = time,
            value = value,
        )
}
