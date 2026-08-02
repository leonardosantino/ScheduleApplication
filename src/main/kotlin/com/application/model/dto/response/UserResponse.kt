package com.application.model.dto.response

import com.application.domain.entity.User
import java.time.Instant

data class UserResponse(
    var id: String?,
    var name: String,
    var lastName: String,
    var phone: String?,
    var email: String?,
    var status: String,
    var createdAt: Instant,
) {
    companion object {
        fun from(user: User) =
            UserResponse(
                id = user.id,
                name = user.name,
                lastName = user.lastName,
                phone = user.phone,
                email = user.email,
                status = user.status,
                createdAt = user.createdAt,
            )
    }
}
