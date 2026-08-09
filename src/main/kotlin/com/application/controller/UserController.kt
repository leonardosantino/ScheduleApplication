package com.application.controller

import com.application.domain.entity.User
import com.application.model.dto.request.UserRequest
import com.application.model.dto.response.UserResponse
import com.application.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService,
) {
    @PostMapping
    fun save(
        @RequestBody user: UserRequest,
    ) = userService
        .save(user)
        ?.let { UserResponse.from(it) }

    @PatchMapping("/phone")
    fun updatePhone(
        @RequestBody request: UpdatePhoneRequest,
    ) = userService
        .updatePhone(request)
        .let { UserResponse.from(it) }

    @GetMapping("/{id}")
    fun findById(
        @PathVariable id: String,
    ) = userService
        .findById(id)
        .ifPresent { UserResponse.from(it) }
}

class UpdatePhoneRequest(
    var id: String,
    var phone: String,
) {
    fun toUser(user: User): User {
        user.phone = phone
        user.updatedAt = Instant.now()

        return user
    }
}
