package com.application.notification

import com.application.domain.entity.UserRole
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
            id = appointment.provider.id,
            role = UserRole.PROVIDER.value,
            title = title,
            body = body,
            url = urlHome,
        )
    }

    @Async
    @EventListener
    fun onCanceled(event: AppointmentCanceledEvent) {
        val appointment = event.appointment

        val cBody = "Seu agendamento com ${appointment.provider.name} foi cancelado."

        pushNotificationService.send(
            id = appointment.customer.id,
            title = title,
            body = cBody,
            url = urlHome,
            role = null,
        )

        val pBody = "Seu agendamento com ${appointment.customer.name} foi cancelado."

        pushNotificationService.send(
            id = appointment.provider.id,
            title = title,
            body = pBody,
            url = urlHome,
            role = UserRole.PROVIDER.value,
        )
    }

    @Async
    @EventListener
    fun onReminder(event: AppointmentReminderEvent) {
        val appointment = event.appointment

        pushNotificationService.send(
            id = appointment.provider.id,
            role = UserRole.PROVIDER.value,
            title = title,
            body = "Seu agendamento com ${appointment.customer.name} começa em 15 minutos.",
            url = urlHome,
        )

        pushNotificationService.send(
            id = appointment.customer.id,
            title = title,
            body = "Seu agendamento com ${appointment.provider.name} começa em 15 minutos.",
            url = urlHome,
            role = null,
        )
    }
}
