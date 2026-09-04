package com.application.common.util

import com.fasterxml.jackson.databind.ObjectMapper
import java.util.Base64.getUrlDecoder

object Jwt {
    private val objectMapper = ObjectMapper()

    fun sub(bearer: String): String =
        bearer
            .removePrefix("Bearer")
            .split('.', limit = 3)[1]
            .let { objectMapper.readTree(getUrlDecoder().decode(it)) }
            .path("sub")
            .asText()
}
