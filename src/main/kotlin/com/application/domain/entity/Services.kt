package com.application.domain.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal
import java.time.Instant

@Entity(name = "services")
class Services(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var userId: String,
    var name: String,
    var description: String,
    var time: Long,
    var value: BigDecimal,
    var status: String,
    var createdAt: Instant,
    var updatedAt: Instant,
)
