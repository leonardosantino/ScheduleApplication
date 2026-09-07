package com.application.repository

import com.application.domain.entity.Appointment
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface AppointmentRepository : MongoRepository<Appointment, String> {
    fun findAllByProviderId(id: String): List<Appointment>

    fun findAllByCustomerId(id: String): List<Appointment>

    fun findAllByProviderIdAndDateAndStatus(
        id: String,
        date: String,
        status: String,
    ): List<Appointment>

    fun existsByCustomerIdAndProviderIdAndDateAndStatus(
        customerId: String,
        providerId: String,
        date: String,
        status: String,
    ): Boolean

    fun existsByProviderIdAndDateAndTimeAndStatus(
        providerId: String,
        date: String,
        time: Int,
        status: String,
    ): Boolean
}
