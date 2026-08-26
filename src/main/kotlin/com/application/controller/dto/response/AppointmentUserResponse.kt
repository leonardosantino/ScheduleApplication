package com.application.controller.dto.response

import com.application.domain.objects.AppointmentUser

data class AppointmentUserResponse(
    var id: String,
    var name: String,
    var lastName: String,
    var phone: String?,
) {
    companion object {
        fun from(user: AppointmentUser) =
            AppointmentUserResponse(
                id = user.id,
                name = user.name,
                lastName = user.lastName,
                phone = user.phone,
            )
    }
}
