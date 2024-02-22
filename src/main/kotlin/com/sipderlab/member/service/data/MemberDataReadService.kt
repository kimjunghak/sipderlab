package com.sipderlab.member.service.data

import com.sipderlab.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberDataReadService(
    private val memberRepository: MemberRepository,
) {

    fun existsByName(name: String): Boolean = memberRepository.existsByName(name)

}