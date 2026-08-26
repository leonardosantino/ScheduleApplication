package com.application.controller.dto.response

import com.application.domain.entity.Announcement
import java.time.Instant

data class AnnouncementResponse(
    var id: String?,
    var slug: String,
    var name: String,
    var description: String,
    var category: String,
    var phone: String,
    var status: String,
    var createdAt: Instant,
    var updatedAt: Instant,
) {
    companion object {
        fun from(announcement: Announcement) =
            AnnouncementResponse(
                id = announcement.id,
                slug = announcement.slug,
                name = announcement.name,
                description = announcement.description,
                category = announcement.category,
                phone = announcement.phone,
                status = announcement.status,
                createdAt = announcement.createdAt,
                updatedAt = announcement.updatedAt,
            )
    }
}
