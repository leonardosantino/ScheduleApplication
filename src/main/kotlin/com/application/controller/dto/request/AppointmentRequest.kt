package com.application.controller.dto.request

import com.application.domain.entity.Appointment
import com.application.domain.entity.CustomerRel
import com.application.domain.entity.IdRelCustomerProvider
import com.application.domain.entity.ProviderRel
import com.application.domain.entity.RelCustomerProvider
import java.time.Instant
import java.time.LocalDate

data class AppointmentRequest(
    var id: String?,
    var user: AppointmentUserRequest,
    var announcement: AppointmentAnnouncementRequest,
    var service: AppointmentServiceRequest,
    var date: LocalDate,
    var times: List<Int>,
) {
    fun toCreate() =
        Appointment(
            id = null,
            user = user.toUser(),
            announcement = announcement.toAnnouncement(),
            service = service.toService(),
            date = date.toString(),
            time = times.first(),
            times = times,
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
        )

    fun toRelCustomerProvider() =
        RelCustomerProvider(
            id = IdRelCustomerProvider(user.id, announcement.id),
            customer =
                CustomerRel(
                    name = user.name,
                    lastName = user.lastName,
                    phone = user.phone,
                ),
            provider =
                ProviderRel(
                    name = announcement.name,
                    description = announcement.description,
                    category = announcement.category,
                    phone = announcement.phone,
                ),
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
        )
}
