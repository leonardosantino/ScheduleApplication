package com.application.controller

import com.application.controller.dto.request.AnnouncementRequest
import com.application.controller.dto.response.AnnouncementResponse
import com.application.service.AnnouncementService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/announcements")
class AnnouncementController(
    private val announcementService: AnnouncementService,
) {
    @PostMapping
    fun save(
        @RequestBody request: AnnouncementRequest,
    ) = announcementService.update(request).let { AnnouncementResponse.from(it) }

    @GetMapping("/{id}")
    fun findById(
        @PathVariable id: String,
    ) = announcementService.findById(id).map { AnnouncementResponse.from(it) }

    @GetMapping("/slug/{slug}")
    fun findBySlug(
        @PathVariable slug: String,
    ) = announcementService.findBySlug(slug).let { AnnouncementResponse.from(it) }
}
