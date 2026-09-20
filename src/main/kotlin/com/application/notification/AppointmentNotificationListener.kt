package com.application.notification

import com.application.notification.dto.AppointmentCanceledEvent
import com.application.notification.dto.AppointmentCreatedEvent
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class AppointmentNotificationListener(
    private val pushNotificationService: PushNotificationService,
) {
    private val app = "Agendily"
    private val homePath = "/"

    @Async
    @EventListener
    fun onCreated(event: AppointmentCreatedEvent) {
        val appointment = event.appointment
        val body = "${appointment.customer.name} agendou ${appointment.service.name} com você."

        pushNotificationService.send(
            title = app,
            body = body,
            id = appointment.provider.id,
            url = homePath,
        )
    }

    @Async
    @EventListener
    fun onCanceled(event: AppointmentCanceledEvent) {
        val appointment = event.appointment

        val pBody = "Seu agendamento com ${appointment.customer.name} foi cancelado."

        pushNotificationService.send(
            title = app,
            body = pBody,
            id = appointment.provider.id,
            url = homePath,
        )

        val cBody = "Seu agendamento com ${appointment.provider.name} foi cancelado."

        pushNotificationService.send(
            title = app,
            body = cBody,
            id = appointment.customer.id,
            url = homePath,
        )
    }
}
