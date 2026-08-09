package com.application.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "availabilities")
class Availability(
    @Id
    var id: String?,
    var days: MutableMap<String, AvailabilityDay>?,
)

class AvailabilityDay(
    var periods: MutableMap<Int, AvailabilityPeriod>?,
)

class AvailabilityPeriod(
    var start: Int,
    var end: Int,
)
