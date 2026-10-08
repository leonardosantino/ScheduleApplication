package com.application.fixture

import com.application.domain.objects.AppointmentStatus
import java.time.LocalDate

object AppointmentFixture {
    fun appointment(
        date: LocalDate,
        times: List<Int>,
        status: AppointmentStatus = AppointmentStatus.CONFIRMED,
    ) = AppointmentRequestFixture
        .appointment(date, times)
        .toCreate()
        .apply { this.status = status.value }
}
