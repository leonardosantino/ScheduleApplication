package com.application.controller.dto.response

import com.application.domain.entity.User

data class UserResponse(
    var id: String?,
    var username: String,
    var name: String,
    var lastName: String,
    var email: String,
    var phone: String?,
    var status: String,
) {
    companion object {
        fun from(user: User) =
            UserResponse(
                id = user.id,
                username = user.username,
                name = user.name,
                lastName = user.lastName,
                email = user.email,
                phone = user.phone,
                status = user.status,
            )
    }
}
