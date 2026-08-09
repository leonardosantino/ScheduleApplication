package com.application.repository

import com.application.domain.entity.Booking
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface BookingRepository : MongoRepository<Booking, String> {
    fun findAllByServiceProviderId(id: String): List<Booking>

    fun findAllByUserId(id: String): List<Booking>
}
