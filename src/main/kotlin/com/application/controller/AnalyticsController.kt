package com.application.controller

import com.application.controller.dto.response.AnalyticsResponse
import com.application.security.UserAuth
import com.application.service.AnalyticsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/analytics")
class AnalyticsController(
    private val analyticsService: AnalyticsService,
) : UserAuth() {
    @GetMapping("/provider/{id}")
    fun findByProviderId(
        @RequestHeader authorization: String,
        @PathVariable id: String,
        @RequestParam from: LocalDate,
        @RequestParam to: LocalDate,
    ): AnalyticsResponse {
        authorize(id, authorization)

        return analyticsService.analyze(id, from, to)
    }
}
