package com.sipderlab.member.domain.entity

import com.sipderlab.common.domain.entity.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Member(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val memberId: Long? = null,
    val name: String,
    val email: String,
    val cellNo: String,
    val password: String,
): BaseEntity()
//TODO 비밀번호 암호화