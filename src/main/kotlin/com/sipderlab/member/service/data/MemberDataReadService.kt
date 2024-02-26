package com.sipderlab.member.service.data

import com.sipderlab.member.domain.entity.Member
import com.sipderlab.member.domain.request.LoginRequest
import com.sipderlab.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class MemberDataReadService(
    private val memberRepository: MemberRepository,
) {

    fun existsByEmail(email: String): Boolean = memberRepository.existsByEmail(email)

    fun findByEmail(loginRequest: LoginRequest): Optional<Member> = memberRepository.findByEmail(loginRequest.email)

    fun findById(memberId: Long): Optional<Member> = memberRepository.findById(memberId)
}