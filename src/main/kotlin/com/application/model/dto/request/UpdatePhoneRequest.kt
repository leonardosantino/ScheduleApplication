package com.application.model.dto.request

import com.application.domain.entity.User
import java.time.Instant

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
