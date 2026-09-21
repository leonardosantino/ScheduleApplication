package com.application.service

import com.application.common.constants.Zone
import com.application.domain.objects.AppointmentStatus
import com.application.notification.dto.AppointmentReminderEvent
import com.application.repository.AppointmentRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.ZonedDateTime

@Component
class AppointmentReminderScheduler(
    private val appointmentRepository: AppointmentRepository,
    private val eventPublisher: ApplicationEventPublisher,
) {
    val variation = 5

    @Scheduled(cron = "0 15,45 7-21 * * *", zone = Zone.AMERICA_SAO_PAULO)
    fun notifyUpcoming() {
        val zone = ZonedDateTime.now(Zone.ID_AMERICA_SAO_PAULO).plusMinutes(15)
        val date = zone.toLocalDate().toString()
        val minutes = zone.hour * 60 + zone.minute

        appointmentRepository
            .findAllByDateAndStatusAndTimeBetween(
                date,
                AppointmentStatus.CONFIRMED.value,
                minutes - variation,
                minutes + variation,
            ).forEach { eventPublisher.publishEvent(AppointmentReminderEvent(it)) }
    }
}
