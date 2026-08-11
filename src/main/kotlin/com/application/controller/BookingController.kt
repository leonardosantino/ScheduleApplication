package com.application.controller

import com.application.controller.dto.request.BookingRequest
import com.application.controller.dto.response.BookingListResponse
import com.application.controller.dto.response.BookingResponse
import com.application.service.BookingService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/bookings")
class BookingController(
    private val bookingService: BookingService,
) {
    @PostMapping
    fun save(
        @RequestBody request: BookingRequest,
    ) = bookingService.save(request).let { BookingResponse.from(it) }

    @GetMapping("/provider/{id}")
    fun findAllByProviderId(
        @PathVariable id: String,
    ) = bookingService.findAllByProviderId(id).let { BookingListResponse.from(it) }

    @GetMapping("/provider/{id}/date/{date}")
    fun findAllByProviderIdAndDate(
        @PathVariable id: String,
        @PathVariable date: LocalDate,
    ) = bookingService.findAllByProviderIdAndDate(id, date).let { BookingListResponse.from(it) }

    @GetMapping("/user/{id}")
    fun findAllByUserId(
        @PathVariable id: String,
    ) = bookingService.findAllByUserId(id).let { BookingListResponse.from(it) }
}
