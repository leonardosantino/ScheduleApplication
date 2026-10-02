package com.application.controller.dto.response

import com.application.domain.entity.Appointment
import org.springframework.data.domain.Page

data class AppointmentsPageResponse(
    val items: List<AppointmentResponse>,
    val page: Int,
    val size: Int,
    val totalElements: Long,
    val totalPages: Int,
) {
    companion object {
        fun from(page: Page<Appointment>) =
            AppointmentsPageResponse(
                items = page.content.map { AppointmentResponse.from(it) },
                page = page.number,
                size = page.size,
                totalElements = page.totalElements,
                totalPages = page.totalPages,
            )
    }
}
