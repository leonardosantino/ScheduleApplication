package com.application.service

import com.application.common.constants.ExMessage
import com.application.controller.dto.request.AppointmentRequest
import com.application.controller.dto.request.AppointmentStatusRequest
import com.application.domain.entity.Appointment
import com.application.domain.objects.AppointmentStatus
import com.application.exception.BadRequestException
import com.application.repository.AppointmentRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class AppointmentService(
    private val appointmentRepository: AppointmentRepository,
    private val relCustomerProviderService: RelCustomerProviderService,
) {
    fun save(request: AppointmentRequest): Appointment {
        if (existsByCustomerIdAndProviderIdAndDateAndStatus(request)) throw BadRequestException(ExMessage.APPOINTMENT_ALREADY_SCHEDULED)
        if (existsByProviderIdAndDateAndStatus(request)) throw BadRequestException(ExMessage.APPOINTMENT_TIME_UNAVAILABLE)

        return appointmentRepository.save(request.toCreate()).also {
            val rel = request.toRelCustomerProvider()
            relCustomerProviderService.save(
                id = rel.id,
                customer = rel.customer,
                provider = rel.provider,
            )
        }
    }

    fun update(request: AppointmentStatusRequest) =
        appointmentRepository.findById(request.id).map {
            appointmentRepository.save(request.toUpdate(it))
        }

    fun findAllByCustomerId(id: String): List<Appointment> = appointmentRepository.findAllByCustomerId(id)

    fun findAllByProviderId(id: String): List<Appointment> = appointmentRepository.findAllByProviderId(id)

    fun findAllByProviderIdAndDate(
        id: String,
        date: LocalDate,
    ): List<Appointment> =
        appointmentRepository.findAllByProviderIdAndDateAndStatus(
            id,
            date.toString(),
            AppointmentStatus.CONFIRMED.value,
        )

    fun existsByCustomerIdAndProviderIdAndDateAndStatus(request: AppointmentRequest): Boolean =
        appointmentRepository.existsByCustomerIdAndProviderIdAndDateAndStatus(
            request.customer.id,
            request.provider.id,
            request.date.toString(),
            AppointmentStatus.CONFIRMED.value,
        )

    fun existsByProviderIdAndDateAndStatus(request: AppointmentRequest): Boolean =
        appointmentRepository.existsByProviderIdAndDateAndTimeAndStatus(
            request.provider.id,
            request.date.toString(),
            request.times.first(),
            AppointmentStatus.CONFIRMED.value,
        )
}
