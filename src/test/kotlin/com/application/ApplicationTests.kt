package com.application

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(
    classes = [Application::class],
    webEnvironment = RANDOM_PORT,
)
class ApplicationTests {
    @Test
    fun contextLoads() {
    }
}
