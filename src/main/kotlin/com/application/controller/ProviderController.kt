package com.application.controller

import com.application.domain.entity.Provider
import com.application.domain.objects.UserStatus
import com.application.service.ProviderService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.text.Normalizer
import java.text.Normalizer.normalize
import java.time.Instant

@RestController
@RequestMapping("/providers")
class ProviderController(
    private val providerService: ProviderService,
) {
    @PostMapping
    fun save(
        @RequestBody request: ProviderRequest,
    ) = providerService.update(request)

    @GetMapping("/{id}")
    fun findById(
        @PathVariable id: String,
    ) = providerService.findById(id)

    @GetMapping("/slug/{slug}")
    fun findBySlug(
        @PathVariable slug: String,
    ) = providerService.findBySlug(slug)
}

data class ProviderRequest(
    var id: String,
    var name: String,
    var role: String,
    var description: String,
) {
    fun toCreate(): Provider =
        Provider(
            id = id,
            slug =
                name.lowercase().let {
                    normalize(it, Normalizer.Form.NFD)
                        .replace(Regex("\\p{InCombiningDiacriticalMarks}+"), "")
                        .replace(" ", "")
                },
            name = name,
            role = role,
            description = description,
            status = UserStatus.ENABLED.value,
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
        )

    fun toUpdate(provider: Provider): Provider {
        provider.name = name
        provider.role = role
        provider.description = description

        return provider
    }
}
