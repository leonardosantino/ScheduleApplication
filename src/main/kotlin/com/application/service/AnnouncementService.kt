package com.application.service

import com.application.common.constants.ExMessage
import com.application.controller.dto.request.AnnouncementRequest
import com.application.exception.NotFoundException
import com.application.repository.AnnouncementRepository
import org.springframework.stereotype.Service

@Service
class AnnouncementService(
    private val announcementRepository: AnnouncementRepository,
) {
    fun update(request: AnnouncementRequest) = announcementRepository.save(request.toCreate())

    fun findById(id: String) = announcementRepository.findById(id)

    fun findBySlug(slug: String) =
        announcementRepository
            .findBySlug(slug)
            .orElseThrow { NotFoundException(ExMessage.ANNOUNCEMENT_NOT_FOUND) }
}
