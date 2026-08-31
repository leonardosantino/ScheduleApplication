package com.application.controller.dto.request

import com.application.domain.objects.AppointmentCustomer

data class AppointmentUserRequest(
    var id: String,
    var name: String,
    var lastName: String?,
    var phone: String,
) {
    fun toCustomer() =
        AppointmentCustomer(
            id = id,
            name = name,
            lastName = lastName,
            phone = phone,
        )
}
