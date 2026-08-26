package com.application.controller.dto.request

import com.application.domain.entity.Appointment
import java.time.Instant
import java.time.LocalDate

data class AppointmentRequest(
    var id: String?,
    var user: AppointmentUserRequest,
    var announcement: AppointmentAnnouncementRequest,
    var service: AppointmentServiceRequest,
    var date: LocalDate,
    var times: List<Int>,
) {
    fun toCreate() =
        Appointment(
            id = null,
            user = user.toUser(),
            announcement = announcement.toAnnouncement(),
            service = service.toService(),
            date = date.toString(),
            time = times.first(),
            times = times,
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
        )
}
