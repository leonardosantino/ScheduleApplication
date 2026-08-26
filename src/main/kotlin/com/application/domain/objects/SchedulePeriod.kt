package com.application.domain.objects

class SchedulePeriod(
    var time: SchedulePeriodTime,
)

class SchedulePeriodTime(
    var start: Int,
    var end: Int,
)
