package com.sipderlab.member.repository

import com.sipderlab.member.domain.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long> {

    fun existsByName(name: String): Boolean
}