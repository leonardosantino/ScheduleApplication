package com.application.controller.dto.request

import com.application.domain.entity.User
import com.application.domain.objects.UserStatus
import java.time.Instant

data class UserRequest(
    var id: String,
    var username: String,
    var status: String?,
    var name: String,
    var lastName: String,
    var birthdate: Instant?,
    var gender: String?,
    var email: String,
    var phone: String?,
) {
    fun toCreate(): User =
        User(
            id = id,
            username = username,
            status = UserStatus.ENABLED.value,
            name = name,
            lastName = lastName,
            birthdate = null,
            gender = null,
            phone = phone,
            email = email,
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
        )
}
