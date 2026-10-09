package com.application.controller.dto.response

import java.math.BigDecimal

data class AnalyticsResponse(
    val from: String,
    val to: String,
    val previousFrom: String,
    val previousTo: String,
    val summary: AnalyticsSummaryResponse,
    val previous: AnalyticsSummaryResponse,
    val series: List<AnalyticsSeriesPointResponse>,
    val topServices: List<AnalyticsServiceResponse>,
    val byWeekday: List<AnalyticsBucketResponse>,
    val byHour: List<AnalyticsBucketResponse>,
    val cancellations: AnalyticsCancellationsResponse,
    val upcoming: AnalyticsUpcomingResponse,
    val totalCustomers: Int,
)

data class AnalyticsUpcomingResponse(
    val days: Int,
    val appointments: Int,
    val revenue: BigDecimal,
)

data class AnalyticsSummaryResponse(
    val realizedRevenue: BigDecimal,
    val lostRevenue: BigDecimal,
    val appointments: Int,
    val averageTicket: BigDecimal,
    val cancellations: Int,
    val cancellationRate: Double,
    val newCustomers: Int,
)

data class AnalyticsSeriesPointResponse(
    val key: String,
    val revenue: BigDecimal,
    val appointments: Int,
)

data class AnalyticsServiceResponse(
    val id: String,
    val name: String,
    val appointments: Int,
    val revenue: BigDecimal,
)

data class AnalyticsBucketResponse(
    val key: Int,
    val appointments: Int,
    val revenue: BigDecimal,
)

data class AnalyticsCancellationsResponse(
    val total: Int,
    val byCustomer: Int,
    val byProvider: Int,
    val lostRevenue: BigDecimal,
)
