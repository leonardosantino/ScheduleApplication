package com.application.repository

import com.application.domain.entity.AvailabilityDay
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AvailabilityDayRepository : JpaRepository<AvailabilityDay, Long>
