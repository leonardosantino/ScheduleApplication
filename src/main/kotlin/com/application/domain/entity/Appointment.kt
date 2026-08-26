package com.application.domain.entity

import com.application.domain.objects.AppointmentAnnouncement
import com.application.domain.objects.AppointmentService
import com.application.domain.objects.AppointmentUser
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "appointments")
data class Appointment(
    @Id
    var id: String?,
    var user: AppointmentUser,
    var announcement: AppointmentAnnouncement,
    var service: AppointmentService,
    var date: String,
    var time: Int,
    var times: List<Int>,
    var createdAt: Instant,
    var updatedAt: Instant,
)
