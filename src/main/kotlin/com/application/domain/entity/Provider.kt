package com.application.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "providers")
class Provider(
    @Id
    var id: String,
    var slug: String,
    var name: String,
    var description: String,
    var category: String,
    var phone: String,
    var status: String,
    var createdAt: Instant,
    var updatedAt: Instant,
)
