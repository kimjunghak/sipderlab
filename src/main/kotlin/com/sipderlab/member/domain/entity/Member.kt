package com.sipderlab.member.domain.entity

import com.sipderlab.common.domain.entity.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Member(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val userId: Long? = null,
    private val name: String,
    private val email: String,
    private val cellNo: String,
    private val password: String,
): BaseEntity() {
}