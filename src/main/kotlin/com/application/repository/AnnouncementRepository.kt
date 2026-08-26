package com.application.repository

import com.application.domain.entity.Announcement
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface AnnouncementRepository : MongoRepository<Announcement, String> {
    fun findBySlug(slug: String): Optional<Announcement>
}
