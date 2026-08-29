package com.application.domain.entity

import com.application.domain.objects.ServiceAnnouncement
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.Instant

@Document(collection = "services")
class Services(
    @Id
    var id: String?,
    var announcement: ServiceAnnouncement,
    var name: String,
    var description: String,
    var time: Int,
    var value: BigDecimal,
    var status: String,
    var createdAt: Instant,
    var updatedAt: Instant,
)
