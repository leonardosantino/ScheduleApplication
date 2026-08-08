package com.application.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "availabilities")
class Availability(
    @Id
    var id: String?,
    var userId: String,
    var days: MutableList<AvailabilityDay>?,
)

class AvailabilityDay(
    var name: String,
    var periods: MutableList<AvailabilityPeriod>?,
)

class AvailabilityPeriod(
    var start: Int,
    var end: Int,
)
