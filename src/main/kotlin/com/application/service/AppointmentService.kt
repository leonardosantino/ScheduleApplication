package com.application.service

import com.application.common.constants.ExMessage
import com.application.controller.dto.request.AppointmentCancellationRequest
import com.application.controller.dto.request.AppointmentRequest
import com.application.controller.dto.request.AppointmentsQueryRequest
import com.application.domain.entity.Appointment
import com.application.domain.objects.AppointmentStatus
import com.application.exception.BadRequestException
import com.application.exception.NotFoundException
import com.application.notification.dto.AppointmentCanceledEvent
import com.application.notification.dto.AppointmentCreatedEvent
import com.application.repository.AppointmentRepository
import com.application.repository.AppointmentRepository.Companion.FIELD_CUSTOMER_ID
import com.application.repository.AppointmentRepository.Companion.FIELD_PROVIDER_ID
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class AppointmentService(
    private val appointmentRepository: AppointmentRepository,
    private val relCustomerProviderService: RelCustomerProviderService,
    private val eventPublisher: ApplicationEventPublisher,
) {
    fun save(request: AppointmentRequest): Appointment {
        if (existsByCustomerIdAndProviderIdAndDateAndStatus(request)) throw BadRequestException(ExMessage.APPOINTMENT_ALREADY_SCHEDULED)
        if (existsByProviderIdAndDateAndStatus(request)) throw BadRequestException(ExMessage.APPOINTMENT_TIME_UNAVAILABLE)

        return appointmentRepository.save(request.toCreate()).also {
            relCustomerProviderService.save(request.toRelCustomerProvider())
            eventPublisher.publishEvent(AppointmentCreatedEvent(it))
        }
    }

    fun cancel(
        id: String,
        request: AppointmentCancellationRequest,
    ): Appointment {
        val appointment =
            appointmentRepository.findById(id).orElseThrow { NotFoundException(ExMessage.APPOINTMENT_NOT_FOUND) }

        return appointmentRepository.save(request.toUpdate(appointment)).also {
            eventPublisher.publishEvent(AppointmentCanceledEvent(it))
        }
    }

    fun findByCustomerId(
        id: String,
        request: AppointmentsQueryRequest,
    ): Page<Appointment> = findByScope(id, FIELD_CUSTOMER_ID, request)

    fun findByProviderId(
        id: String,
        request: AppointmentsQueryRequest,
    ): Page<Appointment> = findByScope(id, FIELD_PROVIDER_ID, request)

    private fun findByScope(
        id: String,
        field: String,
        request: AppointmentsQueryRequest,
    ): Page<Appointment> =
        appointmentRepository.findByScope(
            id = id,
            field = field,
            scope = request.scope,
            page = request.page,
            size = request.size,
        )

    fun findAllByProviderIdAndDateAndStatusConfirmed(
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
