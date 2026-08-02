package com.application.domain.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.Instant

@Entity(name = "users")
class User(
    @Id
    var id: String?,
    var name: String,
    var lastName: String,
    var birthdate: Instant?,
    var email: String,
    var phone: String?,
    var status: String,
    var createdAt: Instant,
    var updatedAt: Instant,
)
