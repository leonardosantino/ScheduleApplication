package com.application.repository

import com.application.domain.entity.Appointment
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface AppointmentRepository : MongoRepository<Appointment, String> {
    fun findAllByProviderId(id: String): List<Appointment>

    fun findAllByCustomerId(id: String): List<Appointment>

    fun findAllByProviderIdAndDate(
        id: String,
        date: String,
    ): List<Appointment>

    fun existsByCustomerIdAndProviderIdAndDate(
        customerId: String,
        providerId: String,
        date: String,
    ): Boolean
}
