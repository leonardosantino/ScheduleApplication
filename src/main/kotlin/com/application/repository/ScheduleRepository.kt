package com.application.repository

import com.application.domain.entity.Schedule
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ScheduleRepository : MongoRepository<Schedule, String>
