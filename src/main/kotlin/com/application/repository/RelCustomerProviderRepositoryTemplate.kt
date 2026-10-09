package com.application.repository

import java.time.Instant

interface RelCustomerProviderRepositoryTemplate {
    fun countByProviderIdAndCreatedAtBetween(
        providerId: String,
        from: Instant,
        to: Instant,
    ): Long
}
