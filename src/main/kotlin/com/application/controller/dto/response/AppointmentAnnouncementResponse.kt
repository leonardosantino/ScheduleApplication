package com.application.controller.dto.response

import com.application.domain.objects.AppointmentAnnouncement

data class AppointmentAnnouncementResponse(
    var id: String,
    var name: String,
    var phone: String,
) {
    companion object {
        fun from(announcement: AppointmentAnnouncement) =
            AppointmentAnnouncementResponse(
                id = announcement.id,
                name = announcement.name,
                phone = announcement.phone,
            )
    }
}
