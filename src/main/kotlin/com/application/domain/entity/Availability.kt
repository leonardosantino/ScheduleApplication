package com.application.domain.entity

import com.application.domain.objects.AvailabilityDay
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "availabilities")
class Availability(
    @Id
    var id: String,
    var days: MutableMap<String, AvailabilityDay>,
)
