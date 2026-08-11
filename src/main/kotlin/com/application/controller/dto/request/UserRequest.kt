package com.application.controller.dto.request

import com.application.domain.entity.User
import com.application.domain.objects.UserStatus
import java.time.Instant

data class UserRequest(
    var id: String,
    var username: String,
    var name: String,
    var lastName: String,
    var email: String,
    var phone: String?,
    var status: String?,
) {
    fun toCreate(): User =
        User(
            id = id,
            username = username,
            name = name,
            lastName = lastName,
            phone = phone,
            email = email,
            status = UserStatus.ENABLED.value,
            birthdate = null,
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
        )
}
