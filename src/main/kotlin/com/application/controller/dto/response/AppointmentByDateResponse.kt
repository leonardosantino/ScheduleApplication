package com.application.controller.dto.response

import com.application.domain.entity.Appointment

data class AppointmentByDateResponse(
    var date: String,
    var time: Int,
    var times: List<Int>,
) {
    companion object {
        fun from(appointment: Appointment) =
            AppointmentByDateResponse(
                date = appointment.date,
                time = appointment.time,
                times = appointment.times,
            )
    }
}
