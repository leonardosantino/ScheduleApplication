package com.application.domain.objects

class AvailabilityPeriod(
    var time: AvailabilityPeriodTime,
)

class AvailabilityPeriodTime(
    var start: Int,
    var end: Int,
)
