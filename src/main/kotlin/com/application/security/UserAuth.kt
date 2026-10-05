package com.application.security

import com.application.common.util.Jwt
import com.application.exception.ForbiddenException
import com.application.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired

abstract class UserAuth {
    @Autowired
    private lateinit var userRepository: UserRepository

    fun authorize(
        id: String,
        authorization: String,
    ) {
        val provider = Jwt.sub(authorization)
        val exists = userRepository.existsByIdAndProviderId(id, provider)

        if (exists.not()) throw ForbiddenException("Forbidden user=$id provider=$provider")
    }
}
