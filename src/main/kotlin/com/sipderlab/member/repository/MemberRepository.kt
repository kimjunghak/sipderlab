package com.sipderlab.member.repository

import com.sipderlab.member.domain.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MemberRepository: JpaRepository<Member, Long> {

    fun existsByEmail(name: String): Boolean
    fun findByEmail(email: String): Optional<Member>
}