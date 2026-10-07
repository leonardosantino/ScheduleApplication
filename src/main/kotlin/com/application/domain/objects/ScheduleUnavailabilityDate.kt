package com.application.domain.objects

class ScheduleUnavailabilityDate(
    var startDate: String,
    var endDate: String,
    var periods: MutableMap<Int, SchedulePeriod>,
)
