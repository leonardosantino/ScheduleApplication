package com.application.domain.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.Instant

@Entity(name = "providers")
class Provider(
    @Id
    var id: String?,
    var slug: String,
    var name: String,
    var description: String,
    var role: String,
    var status: String,
    var createdAt: Instant,
    var updatedAt: Instant,
)
