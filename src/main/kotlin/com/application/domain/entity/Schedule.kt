package com.application.domain.entity

import com.application.domain.objects.ScheduleDay
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "schedule")
class Schedule(
    @Id
    var id: String,
    var days: MutableMap<String, ScheduleDay>,
)
