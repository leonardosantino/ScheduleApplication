package com.application.controller.dto.request

import com.application.domain.entity.Announcement
import com.application.domain.objects.UserStatus
import java.text.Normalizer
import java.text.Normalizer.normalize
import java.time.Instant

data class AnnouncementRequest(
    var id: String,
    var name: String,
    var category: String,
    var description: String,
    var phone: String,
) {
    fun toCreate(): Announcement =
        Announcement(
            id = id,
            slug =
                name.lowercase().let {
                    normalize(it, Normalizer.Form.NFD)
                        .replace(Regex("\\p{InCombiningDiacriticalMarks}+"), "")
                        .replace(Regex("[^\\p{L}\\p{N}]"), "")
                },
            name = name,
            category = category,
            description = description,
            phone = phone,
            status = UserStatus.ENABLED.value,
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
        )

    fun toUpdate(announcement: Announcement): Announcement {
        announcement.name = name
        announcement.category = category
        announcement.description = description
        announcement.phone = phone

        announcement.updatedAt = Instant.now()

        return announcement
    }
}
