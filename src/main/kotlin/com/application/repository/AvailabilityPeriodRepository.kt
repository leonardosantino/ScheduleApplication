package com.application.repository

import com.application.domain.entity.AvailabilityPeriod
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AvailabilityPeriodRepository : JpaRepository<AvailabilityPeriod, Long>
