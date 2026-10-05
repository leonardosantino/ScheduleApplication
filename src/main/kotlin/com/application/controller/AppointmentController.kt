package com.application.controller

import com.application.controller.dto.request.AppointmentCancellationRequest
import com.application.controller.dto.request.AppointmentRequest
import com.application.controller.dto.request.AppointmentsQueryRequest
import com.application.controller.dto.response.AppointmentResponse
import com.application.controller.dto.response.AppointmentsByDateResponse
import com.application.controller.dto.response.AppointmentsPageResponse
import com.application.security.UserAuth
import com.application.service.AppointmentService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/appointments")
class AppointmentController(
    private val appointmentService: AppointmentService,
) : UserAuth() {
    @PostMapping
    fun save(
        @RequestBody request: AppointmentRequest,
    ) = appointmentService.save(request).let { AppointmentResponse.from(it) }

    @PutMapping("/{id}/cancel")
    fun cancel(
        @PathVariable id: String,
        @RequestBody request: AppointmentCancellationRequest,
    ) = AppointmentResponse.from(appointmentService.cancel(id, request))

    @PostMapping("/customer/{id}")
    fun queryByCustomerId(
        @PathVariable id: String,
        @RequestBody request: AppointmentsQueryRequest,
    ) = appointmentService.findByCustomerId(id, request).let { AppointmentsPageResponse.from(it) }

    @PostMapping("/provider/{id}")
    fun queryByProviderId(
        @RequestHeader authorization: String,
        @PathVariable id: String,
        @RequestBody request: AppointmentsQueryRequest,
    ): AppointmentsPageResponse {
        authorize(id, authorization)

        return appointmentService.findByProviderId(id, request).let { AppointmentsPageResponse.from(it) }
    }

    @GetMapping("/provider/{id}/date/{date}")
    fun findAllByProviderIdAndDate(
        @PathVariable id: String,
        @PathVariable date: LocalDate,
    ) = appointmentService.findAllByProviderIdAndDate(id, date).let { AppointmentsByDateResponse.from(it) }
}
