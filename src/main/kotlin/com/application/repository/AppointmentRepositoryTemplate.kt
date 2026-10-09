package com.application.repository

import com.application.domain.entity.Appointment
import com.application.domain.objects.AppointmentScope
import org.springframework.data.domain.Page

interface AppointmentRepositoryTemplate {
    fun findByScope(
        id: String,
        field: String,
        scope: AppointmentScope,
        page: Int,
        size: Int,
    ): Page<Appointment>

    fun findByPeriod(
        providerId: String,
        from: String,
        to: String,
    ): List<Appointment>
}
