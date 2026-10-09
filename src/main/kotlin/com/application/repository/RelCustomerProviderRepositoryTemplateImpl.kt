package com.application.repository

import com.application.common.constants.DocField
import com.application.domain.entity.RelCustomerProvider
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import java.time.Instant

class RelCustomerProviderRepositoryTemplateImpl(
    private val mongoTemplate: MongoTemplate,
) : RelCustomerProviderRepositoryTemplate {
    companion object {
        val entityClass = RelCustomerProvider::class.java
    }

    override fun countByProviderIdAndCreatedAtBetween(
        providerId: String,
        from: Instant,
        to: Instant,
    ): Long {
        val criteria =
            Criteria
                .where(DocField.PROVIDER_ID)
                .`is`(providerId)
                .and("createdAt")
                .gte(from)
                .lt(to)

        return mongoTemplate.count(Query(criteria), entityClass)
    }
}
