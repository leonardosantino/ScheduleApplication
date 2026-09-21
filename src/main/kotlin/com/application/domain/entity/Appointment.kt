package com.application.domain.entity

import com.application.domain.objects.AppointmentCancellation
import com.application.domain.objects.AppointmentCustomer
import com.application.domain.objects.AppointmentProvider
import com.application.domain.objects.AppointmentService
import com.application.domain.objects.AppointmentStatus
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "appointments")
data class Appointment(
    @Id
    var id: String?,
    var customer: AppointmentCustomer,
    var provider: AppointmentProvider,
    var service: AppointmentService,
    var date: String,
    var time: Int,
    var times: List<Int>,
    var status: String,
    var cancellation: AppointmentCancellation? = null,
    var createdAt: Instant,
    var updatedAt: Instant,
) {
    fun isCanceled(): Boolean = status == AppointmentStatus.CANCELED.value
}
