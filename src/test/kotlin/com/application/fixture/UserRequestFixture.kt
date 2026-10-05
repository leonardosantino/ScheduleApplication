package com.application.fixture

import com.application.controller.dto.request.UserRequest
import com.application.domain.objects.ExternalProvider
import com.application.domain.objects.UserRole
import com.application.domain.objects.UserStatus

object UserRequestFixture {
    val user6f13 =
        UserRequest(
            provider =
                ExternalProvider(
                    id = "5f1c2a9e-3b7d-4e68-9a41-c8d27e0b6f13",
                    username = "google_104827361950284716352",
                ),
            role = UserRole.CUSTOMER,
            name = "Leonardo",
            lastName = "Santino",
            birthdate = null,
            gender = null,
            email = "leonarsantin@gmail.com",
            phone = "+5581981688210",
            status = UserStatus.ENABLED.value,
        )
}
