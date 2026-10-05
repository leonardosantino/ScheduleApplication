package com.application.service

import com.application.ApplicationTests
import com.application.controller.dto.request.UpdatePhoneRequest
import com.application.domain.objects.UserStatus
import com.application.fixture.UserRequestFixture
import com.application.repository.UserRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class UserServiceTests : ApplicationTests() {
    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var userRepository: UserRepository

    @BeforeEach
    fun cleanUp() {
        userRepository.deleteAll()
    }

    @Test
    fun shouldSaveOrGetAndUpdateWhenExists() {
        val request = UserRequestFixture.user6f13

        val user = userService.save(request)

        assertNotNull(user.id)
        assertEquals(request.provider, user.provider)
        assertEquals(request.name, user.name)
        assertEquals(request.lastName, user.lastName)
        assertEquals(request.email, user.email)
        assertEquals(request.phone, user.phone)
        assertEquals(request.role, user.role)
        assertEquals(UserStatus.ENABLED.value, user.status)

        val requestWhenExists = UserRequestFixture.user6f13.copy(name = "Leo", lastName = "Sant")
        val userWhenExists = userService.save(requestWhenExists)

        assertEquals(user.id, userWhenExists.id)
        assertEquals(requestWhenExists.name, userWhenExists.name)
        assertEquals(requestWhenExists.lastName, userWhenExists.lastName)
        assertEquals(user.createdAt.toEpochMilli(), userWhenExists.createdAt.toEpochMilli())
        assertNotEquals(user.updatedAt.toEpochMilli(), userWhenExists.updatedAt.toEpochMilli())
    }

    @Test
    fun shouldUpdatePhone() {
        val user6f13 = userService.save(UserRequestFixture.user6f13)
        val request = UpdatePhoneRequest(id = user6f13.id.toString(), phone = "+5581999990000")

        val user = userService.updatePhone(request)

        assertEquals(user6f13.id, user.id)
        assertEquals(request.phone, user.phone)

        assertEquals(user6f13.createdAt.toEpochMilli(), user.createdAt.toEpochMilli())
        assertNotEquals(user6f13.updatedAt.toEpochMilli(), user.updatedAt.toEpochMilli())
    }

    @Test
    fun shouldDeleteUser() {
        val user6f13 = userService.save(UserRequestFixture.user6f13)

        userService.deleteById(user6f13.id.toString())

        val user = userService.findById(user6f13.id.toString())

        assertTrue(user.isEmpty)
    }
}
