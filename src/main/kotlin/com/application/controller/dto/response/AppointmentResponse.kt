package com.application.controller.dto.response

import com.application.domain.entity.Appointment
import java.time.Instant

data class AppointmentResponse(
    var id: String?,
    var user: AppointmentUserResponse,
    var announcement: AppointmentAnnouncementResponse,
    var service: AppointmentServiceResponse,
    var date: String,
    var time: Int,
    var times: List<Int>,
    var createdAt: Instant,
    var updatedAt: Instant,
) {
    companion object {
        fun from(appointment: Appointment) =
            AppointmentResponse(
                id = appointment.id,
                user = AppointmentUserResponse.from(appointment.user),
                announcement = AppointmentAnnouncementResponse.from(appointment.announcement),
                service = AppointmentServiceResponse.from(appointment.service),
                date = appointment.date,
                time = appointment.time,
                times = appointment.times,
                createdAt = appointment.createdAt,
                updatedAt = appointment.updatedAt,
            )
    }
}
