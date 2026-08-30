package com.application.service

import com.application.common.constants.ExMessage
import com.application.controller.dto.request.AppointmentRequest
import com.application.domain.entity.Appointment
import com.application.exception.BadRequestException
import com.application.repository.AppointmentRepository
import org.slf4j.LoggerFactory.getLogger
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class AppointmentService(
    private val appointmentRepository: AppointmentRepository,
    private val relCustomerProviderService: RelCustomerProviderService,
) {
    private val log = getLogger(this::class.java)

    fun save(request: AppointmentRequest): Appointment =
        try {
            appointmentRepository.save(request.toCreate()).also {
                val rel = request.toRelCustomerProvider()
                relCustomerProviderService.save(
                    id = rel.id,
                    customer = rel.customer,
                    provider = rel.provider,
                )
            }
        } catch (ex: DuplicateKeyException) {
            log.error(ex.message)
            throw BadRequestException(ExMessage.APPOINTMENT_TIME_UNAVAILABLE)
        }

    fun findAllByProviderId(id: String): List<Appointment> = appointmentRepository.findAllByProviderId(id)

    fun findAllByCustomerId(id: String): List<Appointment> = appointmentRepository.findAllByCustomerId(id)

    fun findAllByProviderIdAndDate(
        id: String,
        date: LocalDate,
    ): List<Appointment> = appointmentRepository.findAllByProviderIdAndDate(id, date.toString())
}
