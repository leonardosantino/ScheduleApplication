package com.application.service

import com.application.common.constants.ExMessage
import com.application.domain.entity.Booking
import com.application.exception.BadRequestException
import com.application.model.dto.request.BookingRequest
import com.application.repository.BookingRepository
import org.slf4j.LoggerFactory.getLogger
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.ZoneOffset

@Service
class BookingService(
    private val bookingRepository: BookingRepository,
) {
    private val log = getLogger(this::class.java)

    fun save(request: BookingRequest): Booking =
        try {
            bookingRepository.save(request.toCreate())
        } catch (ex: DuplicateKeyException) {
            log.error(ex.message)
            throw BadRequestException(ExMessage.bookingTimeUnavailable)
        }

    fun findAllByProviderId(id: String): List<Booking> = bookingRepository.findAllByProviderId(id)

    fun findAllByUserId(id: String): List<Booking> = bookingRepository.findAllByUserId(id)

    fun findAllByProviderIdAndDate(
        providerId: String,
        date: LocalDate,
    ): List<Booking> {
        val instant = date.atStartOfDay(ZoneOffset.UTC).toInstant()

        return bookingRepository.findAllByProviderIdAndDate(providerId, instant)
    }
}
