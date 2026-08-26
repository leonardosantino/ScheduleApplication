package com.application.repository

import com.application.domain.entity.Appointment
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface AppointmentRepository : MongoRepository<Appointment, String> {
    fun findAllByAnnouncementId(id: String): List<Appointment>

    fun findAllByUserId(id: String): List<Appointment>

    fun findAllByAnnouncementIdAndDate(
        id: String,
        date: String,
    ): List<Appointment>
}
