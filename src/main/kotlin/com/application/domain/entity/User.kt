package com.application.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "users")
class User(
    @Id
    var id: String?,
    var username: String,
    var name: String,
    var lastName: String,
    var birthdate: Instant?,
    var email: String,
    var phone: String?,
    var status: String,
    var createdAt: Instant,
    var updatedAt: Instant,
)
