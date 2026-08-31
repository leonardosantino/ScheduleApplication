package com.application.controller.dto.request

import com.application.domain.entity.Appointment
import com.application.domain.entity.RelCustomerProvider
import com.application.domain.objects.AppointmentStatus
import com.application.domain.objects.CustomerRel
import com.application.domain.objects.IdRelCustomerProvider
import com.application.domain.objects.ProviderRel
import java.time.Instant
import java.time.LocalDate

data class AppointmentRequest(
    var id: String?,
    var customer: AppointmentUserRequest,
    var provider: AppointmentProviderRequest,
    var service: AppointmentServiceRequest,
    var date: LocalDate,
    var times: List<Int>,
) {
    fun toCreate() =
        Appointment(
            id = null,
            customer = customer.toCustomer(),
            provider = provider.toProvider(),
            service = service.toService(),
            date = date.toString(),
            time = times.first(),
            times = times,
            status = AppointmentStatus.CONFIRMED.value,
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
        )

    fun toRelCustomerProvider() =
        RelCustomerProvider(
            id = IdRelCustomerProvider(customer.id, provider.id),
            customer =
                CustomerRel(
                    name = customer.name,
                    lastName = customer.lastName,
                    phone = customer.phone,
                ),
            provider =
                ProviderRel(
                    name = provider.name,
                    slug = provider.slug,
                    description = provider.description,
                    category = provider.category,
                    phone = provider.phone,
                ),
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
        )
}
