package com.application.service

import com.application.model.dto.request.UserRequest
import com.application.model.dto.response.UserResponse
import com.application.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun save(request: UserRequest) =
        userRepository
            .findById(request.id)
            .orElse(
                userRepository.save(request.toCreate()),
            )?.let { UserResponse.from(it) }
}
