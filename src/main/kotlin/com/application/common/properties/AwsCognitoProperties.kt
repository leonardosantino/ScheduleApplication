package com.application.common.properties

import org.springframework.context.annotation.Configuration

@Configuration
data class AwsCognitoProperties(
    var userPoolId: String = "",
)
