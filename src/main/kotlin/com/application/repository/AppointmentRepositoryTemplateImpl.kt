package com.application.repository

import com.application.common.constants.DocField
import com.application.common.constants.Zone
import com.application.domain.entity.Appointment
import com.application.domain.objects.AppointmentScope
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import java.time.ZonedDateTime

class AppointmentRepositoryTemplateImpl(
    private val mongoTemplate: MongoTemplate,
) : AppointmentRepositoryTemplate {
    companion object {
        val entityClass = Appointment::class.java
    }

    override fun findByScope(
        id: String,
        field: String,
        scope: AppointmentScope,
        page: Int,
        size: Int,
    ): Page<Appointment> {
        val now = ZonedDateTime.now(Zone.ID_AMERICA_SAO_PAULO)
        val date = now.toLocalDate().toString()
        val threshold = now.hour * 60 + now.minute - DocField.ONGOING_TOLERANCE

        val owner = Criteria.where(field).`is`(id)
        val criteria =
            when (scope) {
                AppointmentScope.TODAY ->
                    owner
                        .and("date")
                        .`is`(date)
                        .and("time")
                        .gt(threshold)

                AppointmentScope.UPCOMING ->
                    owner.and("date").gt(date)

                AppointmentScope.PAST ->
                    Criteria().andOperator(
                        owner,
                        Criteria().orOperator(
                            Criteria.where("date").lt(date),
                            Criteria
                                .where("date")
                                .`is`(date)
                                .and("time")
                                .lte(threshold),
                        ),
                    )
            }

        val direction = if (scope == AppointmentScope.PAST) Sort.Direction.DESC else Sort.Direction.ASC
        val pageable = PageRequest.of(page, size, Sort.by(direction, "date", "time"))

        val total = mongoTemplate.count(Query(criteria), entityClass)
        val items = mongoTemplate.find(Query(criteria).with(pageable), entityClass)

        return PageImpl(items, pageable, total)
    }

    override fun findByPeriod(
        providerId: String,
        from: String,
        to: String,
    ): List<Appointment> {
        val criteria =
            Criteria
                .where(DocField.PROVIDER_ID)
                .`is`(providerId)
                .and("date")
                .gte(from)
                .lte(to)

        return mongoTemplate.find(Query(criteria), entityClass)
    }
}
