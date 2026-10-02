package com.application.common.util

import tools.jackson.databind.json.JsonMapper
import java.util.Base64.getUrlDecoder

object Jwt {
    private val mapper = JsonMapper()

    fun sub(bearer: String): String =
        bearer
            .removePrefix("Bearer")
            .split('.', limit = 3)[1]
            .let { mapper.readTree(getUrlDecoder().decode(it)) }
            .path("sub")
            .asString()
}
