package com.application.domain.objects

enum class CanceledBy(
    value: String,
) {
    CUSTOMER("Cliente"),
    PROVIDER("Prestador"),
    SYSTEM("Sistema"),
}
