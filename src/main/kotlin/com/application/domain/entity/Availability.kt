package com.application.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Availability(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var userId: String,
    @OneToMany(mappedBy = "availabilityId")
    var days: MutableList<AvailabilityDay>?,
)

@Entity
class AvailabilityDay(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var availabilityId: Long,
    var name: String,
    @OneToMany(mappedBy = "availabilityDayId")
    var periods: MutableList<AvailabilityPeriod>?,
)

@Entity
class AvailabilityPeriod(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var availabilityDayId: Long,
    @Column(name = "start_time")
    var start: Int,
    @Column(name = "end_time")
    var end: Int,
)
