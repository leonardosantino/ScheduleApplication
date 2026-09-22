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
        val body = "${event.appointment.customer.name} agendou ${event.appointment.service.name} com você."

        pushNotificationService.send(
            id = event.appointment.provider.id,
            role = UserRole.PROVIDER.value,
            title = title,
            body = body,
            url = urlHome,
        )
    }

    @Async
    @EventListener
    fun onCanceled(event: AppointmentCanceledEvent) {
        if (event.appointment.isCalledByProvider()) {
            val cBody = "Seu agendamento com ${event.appointment.provider.name} foi cancelado."

            pushNotificationService.send(
                id = event.appointment.customer.id,
                title = title,
                body = cBody,
                url = urlHome,
                role = null,
            )
        } else {
            val pBody = "Seu agendamento com ${event.appointment.customer.name} foi cancelado."

            pushNotificationService.send(
                id = event.appointment.provider.id,
                title = title,
                body = pBody,
                url = urlHome,
                role = UserRole.PROVIDER.value,
            )
        }
    }

    @Async
    @EventListener
    fun onReminder(event: AppointmentReminderEvent) {
        pushNotificationService.send(
            id = event.appointment.provider.id,
            role = UserRole.PROVIDER.value,
            title = title,
            body = "15 minutos para seu agendamento com ${event.appointment.customer.name}.",
            url = urlHome,
        )

        pushNotificationService.send(
            id = event.appointment.customer.id,
            title = title,
            body = "15 minutos para seu agendamento com ${event.appointment.provider.name}.",
            url = urlHome,
            role = null,
        )
    }
}
