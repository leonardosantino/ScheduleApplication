package com.application.controller

import com.application.model.dto.request.UserRequest
import com.application.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService,
) {
    @PostMapping
    fun save(
        @RequestBody user: UserRequest,
    ) = userService.save(user)
}
