package com.application.controller

import com.application.service.BookingRequest
import com.application.service.BookingService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/bookings")
class BookingController(
    private val bookingService: BookingService,
) {
    @PostMapping
    fun save(
        @RequestBody request: BookingRequest,
    ) = bookingService.save(request)

    @GetMapping("/provider/{id}")
    fun findAllByProviderId(
        @PathVariable id: String,
    ) = bookingService.findAllByProviderId(id)

    @GetMapping("/user/{id}")
    fun findAllByUserId(
        @PathVariable id: String,
    ) = bookingService.findAllByUserId(id)
}
