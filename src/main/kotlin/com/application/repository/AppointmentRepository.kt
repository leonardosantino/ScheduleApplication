package com.application.repository

import com.application.domain.entity.Appointment
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface AppointmentRepository :
    MongoRepository<Appointment, String>,
    AppointmentRepositoryTemplate {
    companion object {
        const val FIELD_PROVIDER_ID = "provider.id"
        const val FIELD_CUSTOMER_ID = "customer.id"
    }

    fun findAllByProviderIdAndDateAndStatus(
        id: String,
        date: String,
        status: String,
    ): List<Appointment>

    fun findAllByDateAndStatusAndTimeBetween(
        date: String,
        status: String,
        fromTime: Int,
        toTime: Int,
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
