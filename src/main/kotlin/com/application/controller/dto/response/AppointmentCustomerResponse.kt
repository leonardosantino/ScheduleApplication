package com.application.controller.dto.response

import com.application.domain.objects.AppointmentCustomer

data class AppointmentCustomerResponse(
    var name: String,
    var lastName: String?,
    var phone: String?,
) {
    companion object {
        fun from(user: AppointmentCustomer) =
            AppointmentCustomerResponse(
                name = user.name,
                lastName = user.lastName,
                phone = user.phone,
            )
    }
}
