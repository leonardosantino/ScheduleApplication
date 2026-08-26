package com.application.controller.dto.request

import com.application.domain.objects.AppointmentAnnouncement

data class AppointmentAnnouncementRequest(
    var id: String,
    var name: String,
    var phone: String,
) {
    fun toAnnouncement() =
        AppointmentAnnouncement(
            id = id,
            name = name,
            phone = phone,
        )
}
