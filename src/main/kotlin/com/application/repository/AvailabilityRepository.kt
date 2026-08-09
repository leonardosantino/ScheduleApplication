package com.application.repository

import com.application.domain.entity.Availability
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface AvailabilityRepository : MongoRepository<Availability, String>
