package com.application.notification

import com.application.notification.dto.AppointmentCanceledEvent
import com.application.notification.dto.AppointmentCreatedEvent
import com.application.notification.dto.AppointmentReminderEvent
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class AppointmentNotificationListener(
    private val pushNotificationService: PushNotificationService,
) {
    private val title = "Agendily"
    private val urlHome = "/"

    @Async
    @EventListener
    fun onCreated(event: AppointmentCreatedEvent) {
        val appointment = event.appointment
        val body = "${appointment.customer.name} agendou ${appointment.service.name} com você."

        pushNotificationService.send(
            title = title,
            body = body,
            id = appointment.provider.id,
            url = urlHome,
        )
    }

    @Async
    @EventListener
    fun onCanceled(event: AppointmentCanceledEvent) {
        val appointment = event.appointment

        val pBody = "Seu agendamento com ${appointment.customer.name} foi cancelado."

        pushNotificationService.send(
            title = title,
            body = pBody,
            id = appointment.provider.id,
            url = urlHome,
        )

        val cBody = "Seu agendamento com ${appointment.provider.name} foi cancelado."

        pushNotificationService.send(
            title = title,
            body = cBody,
            id = appointment.customer.id,
            url = urlHome,
        )
    }

    @Async
    @EventListener
    fun onReminder(event: AppointmentReminderEvent) {
        val appointment = event.appointment

        pushNotificationService.send(
            title = title,
            body = "Seu agendamento com ${appointment.customer.name} começa em 15 minutos.",
            id = appointment.provider.id,
            url = urlHome,
        )

        pushNotificationService.send(
            title = title,
            body = "Seu agendamento com ${appointment.provider.name} começa em 15 minutos.",
            id = appointment.customer.id,
            url = urlHome,
        )
    }
}
