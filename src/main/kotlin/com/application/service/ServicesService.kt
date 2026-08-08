package com.application.service

import com.application.domain.entity.Services
import com.application.domain.objects.ServiceStatus
import com.application.repository.ServicesRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.Instant

@Service
class ServicesService(
    private val servicesRepository: ServicesRepository,
) {
    fun save(request: ServiceRequest) = servicesRepository.save(request.toCreate())

    fun findAllByUserId(id: String) =
        servicesRepository
            .findAllByUserId(id)
            .let { ServiceResponse.toListResponse(it) }
}

data class ServiceResponse(
    val items: List<Services>,
) {
    companion object {
        fun toListResponse(services: List<Services>) = ServiceResponse(services)
    }
}

data class ServiceRequest(
    var id: String?,
    var userId: String,
    var name: String,
    var description: String,
    var time: Long,
    var value: BigDecimal,
    var status: String?,
    var createdAt: Instant?,
    var updatedAt: Instant?,
) {
    fun toCreate() =
        Services(
            id = null,
            userId = userId,
            name = name,
            description = description,
            time = time,
            value = value,
            status = ServiceStatus.ENABLED.value,
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
        )
}
