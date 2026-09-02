package com.application.controller.dto.response

import com.application.domain.entity.Appointment

data class AppointmentResponse(
    var customer: AppointmentCustomerResponse,
    var provider: AppointmentProviderResponse,
    var service: AppointmentServiceResponse,
    var date: String,
    var time: Int,
    var times: List<Int>,
    var status: String?,
) {
    companion object {
        fun from(appointment: Appointment) =
            AppointmentResponse(
                customer = AppointmentCustomerResponse.from(appointment.customer),
                provider = AppointmentProviderResponse.from(appointment.provider),
                service = AppointmentServiceResponse.from(appointment.service),
                date = appointment.date,
                time = appointment.time,
                times = appointment.times,
                status = appointment.status,
            )
    }
}
