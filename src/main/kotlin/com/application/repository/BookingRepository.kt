package com.application.repository

import com.application.domain.entity.Booking
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
interface BookingRepository : MongoRepository<Booking, String> {
    fun findAllByProviderId(id: String): List<Booking>

    fun findAllByUserId(id: String): List<Booking>

    fun findAllByProviderIdAndDate(
        id: String,
        date: Instant,
    ): List<Booking>
}
