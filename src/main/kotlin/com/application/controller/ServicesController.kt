package com.application.controller

import com.application.service.ServiceRequest
import com.application.service.ServicesService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/services")
class ServicesController(
    private val servicesService: ServicesService,
) {
    @PostMapping
    fun save(
        @RequestBody user: ServiceRequest,
    ) = servicesService.save(user)

    @GetMapping
    fun findAllByUserId(
        @RequestParam id: String,
    ) = servicesService.findAllByUserId(id)
}
