package com.application.common.configs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient

@Configuration
class CognitoConfig {
    @Bean
    fun cognitoIdentityProviderClient(): CognitoIdentityProviderClient =
        CognitoIdentityProviderClient
            .builder()
            .build()
}
