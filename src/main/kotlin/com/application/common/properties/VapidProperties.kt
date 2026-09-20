package com.application.common.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "vapid")
data class VapidProperties(
    var publicKey: String = "",
    var privateKey: String = "",
    var subject: String = "",
)
