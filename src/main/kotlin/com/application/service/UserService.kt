package com.application.service

import com.application.controller.UpdatePhoneRequest
import com.application.domain.entity.User
import com.application.model.dto.request.UserRequest
import com.application.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun save(request: UserRequest) =
        findById(request.id)
            .orElse(
                userRepository.save(request.toCreate()),
            )

    fun updatePhone(request: UpdatePhoneRequest): User {
        val user = findById(request.id).orElseThrow()

        return userRepository.save(request.toUser(user))
    }

    fun findById(id: String) = userRepository.findById(id)
}
