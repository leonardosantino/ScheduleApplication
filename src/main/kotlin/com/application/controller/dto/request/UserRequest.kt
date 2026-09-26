package com.application.controller.dto.request

import com.application.domain.entity.User
import com.application.domain.objects.ExternalProvider
import com.application.domain.objects.UserRole
import com.application.domain.objects.UserStatus
import java.time.Instant

data class UserRequest(
    var provider: ExternalProvider,
    var role: UserRole,
    var name: String,
    var lastName: String?,
    var birthdate: Instant?,
    var gender: String?,
    var email: String,
    var phone: String?,
    var status: String?,
) {
    fun toCreate(): User =
        User(
            id = null,
            provider = provider,
            role = role,
            name = name,
            lastName = lastName,
            birthdate = birthdate,
            gender = gender,
            phone = phone,
            email = email,
            status = UserStatus.ENABLED.value,
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
        )

    fun toUpdate(user: User): User {
        user.name = name
        user.lastName = lastName
        user.updatedAt = Instant.now()

        return user
    }
}
