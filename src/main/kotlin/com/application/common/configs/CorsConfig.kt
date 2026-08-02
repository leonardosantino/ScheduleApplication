package com.application.common.configs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Profile("dev")
@Configuration
class CorsConfig {
    @Bean
    fun corsFilter(): CorsFilter {
        val config =
            CorsConfiguration().apply {
                allowedOriginPatterns = listOf("http://localhost:*")
                allowedHeaders = listOf("*")
                allowedMethods = listOf("*")
                allowCredentials = false
            }

        val source =
            UrlBasedCorsConfigurationSource().apply {
                registerCorsConfiguration("/**", config)
            }

        return CorsFilter(source)
    }
}
