package com.application.fixture

import com.application.controller.dto.request.AppointmentProviderRequest
import com.application.controller.dto.request.AppointmentRequest
import com.application.controller.dto.request.AppointmentServiceRequest
import com.application.controller.dto.request.AppointmentUserRequest
import java.math.BigDecimal
import java.time.LocalDate

object AppointmentRequestFixture {
    const val PROVIDER_ID = "6abee7ddc47b109c4a211592"

    val customer =
        AppointmentUserRequest(
            id = "6abee317c47b109c4a211591",
            name = "Leonardo",
            lastName = "Santino",
            phone = "+5587981062311",
        )

    val otherCustomer =
        AppointmentUserRequest(
            id = "6abee9d2c47b109c4a211594",
            name = "Mariana",
            lastName = "Souza",
            phone = "+5581999887766",
        )

    val provider =
        AppointmentProviderRequest(
            id = PROVIDER_ID,
            name = "Support Local",
            slug = "supportlocal",
            description = "For support only",
            category = "Support",
            phone = "+5581981688210",
        )

    val otherProvider =
        AppointmentProviderRequest(
            id = "6abee7ddc47b109c4a211599",
            name = "Studio Bela",
            slug = "studiobela",
            description = "Cabelo e estética",
            category = "Beauty",
            phone = "+5581988776655",
        )

    val service =
        AppointmentServiceRequest(
            id = "6ac6a57f5f546a9da957d5d9",
            name = "Support",
            description = "Support only",
            time = 30,
            value = BigDecimal("39.9"),
        )

    fun appointment(
        date: LocalDate,
        times: List<Int>,
        customer: AppointmentUserRequest = this.customer,
        provider: AppointmentProviderRequest = this.provider,
    ) = AppointmentRequest(
        id = null,
        customer = customer,
        provider = provider,
        service = service,
        date = date,
        times = times,
    )
}
