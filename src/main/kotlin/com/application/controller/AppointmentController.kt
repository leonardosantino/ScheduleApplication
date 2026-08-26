package com.application.controller

import com.application.controller.dto.request.AppointmentRequest
import com.application.controller.dto.response.AppointmentResponse
import com.application.controller.dto.response.AppointmentsResponse
import com.application.service.AppointmentService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/appointments")
class AppointmentController(
    private val appointmentService: AppointmentService,
) {
    @PostMapping
    fun save(
        @RequestBody request: AppointmentRequest,
    ) = appointmentService.save(request).let { AppointmentResponse.from(it) }

    @GetMapping("/provider/{id}")
    fun findAllByProviderId(
        @PathVariable id: String,
    ) = appointmentService.findAllByProviderId(id).let { AppointmentsResponse.from(it) }

    @GetMapping("/provider/{id}/date/{date}")
    fun findAllByProviderIdAndDate(
        @PathVariable id: String,
        @PathVariable date: LocalDate,
    ) = appointmentService.findAllByProviderIdAndDate(id, date).let { AppointmentsResponse.from(it) }

    @GetMapping("/user/{id}")
    fun findAllByUserId(
        @PathVariable id: String,
    ) = appointmentService.findAllByUserId(id).let { AppointmentsResponse.from(it) }
}
