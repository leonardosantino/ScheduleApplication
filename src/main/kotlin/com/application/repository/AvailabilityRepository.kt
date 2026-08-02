package com.application.repository

import com.application.domain.entity.Availability
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AvailabilityRepository : JpaRepository<Availability, Long>
