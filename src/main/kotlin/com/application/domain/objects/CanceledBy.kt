package com.application.domain.objects

enum class CanceledBy(
    val value: String,
) {
    CUSTOMER("Cliente"),
    PROVIDER("Prestador"),
    SYSTEM("Sistema"),
}
