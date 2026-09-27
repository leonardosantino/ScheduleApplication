package com.application.controller

import com.application.common.util.Jwt
import com.application.common.util.logger
import com.application.controller.dto.request.UpdatePhoneRequest
import com.application.controller.dto.request.UserRequest
import com.application.controller.dto.response.UserResponse
import com.application.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService,
) {
    private val log = logger()

    @PostMapping
    fun save(
        @RequestHeader authorization: String,
        @RequestBody request: UserRequest,
    ) = userService
        .save(request)
        .let { UserResponse.from(it) }
        .also {
            log.info("Accessing user=${it.id} auth=${Jwt.sub(authorization)}")
        }

    @PatchMapping("/phone")
    fun updatePhone(
        @RequestBody request: UpdatePhoneRequest,
    ) = userService
        .updatePhone(request)
        .let { UserResponse.from(it) }

    @GetMapping("/{id}")
    fun findById(
        @PathVariable id: String,
    ) = userService.findById(id).map { UserResponse.from(it) }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(
        @RequestHeader authorization: String,
        @PathVariable id: String,
    ) = userService
        .delete(id, Jwt.sub(authorization))
        .also {
            log.info("Deleting user=$id auth=${Jwt.sub(authorization)}")
        }
}
