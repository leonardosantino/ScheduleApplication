package com.application.domain.entity

import com.application.domain.objects.ExternalProvider
import com.application.domain.objects.UserRole
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "users")
class User(
    @Id
    var id: String?,
    var provider: ExternalProvider,
    var role: UserRole,
    var name: String,
    var lastName: String?,
    var birthdate: Instant?,
    var gender: String?,
    var email: String,
    var phone: String?,
    var status: String,
    var createdAt: Instant,
    var updatedAt: Instant,
)
