package com.sipderlab.common.domain.entity

import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime


@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class BaseEntity {
    @CreatedDate
    var createdAt: LocalDateTime? = LocalDateTime.now()
        protected set

    @LastModifiedDate
    var updatedAt: LocalDateTime? = LocalDateTime.now()
        protected set
}