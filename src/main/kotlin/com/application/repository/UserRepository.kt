package com.application.repository

import com.application.domain.entity.User
import com.application.domain.objects.UserRole
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository : MongoRepository<User, String> {
    fun findByProviderIdAndRole(
        id: String,
        role: UserRole,
    ): Optional<User>

    fun existsByIdAndProviderId(
        id: String,
        providerId: String,
    ): Boolean
}
