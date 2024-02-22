package com.sipderlab.member.service.data

import com.sipderlab.member.domain.entity.Member
import com.sipderlab.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberDataWriteService(
    private val memberRepository: MemberRepository
) {
    fun saveMember(member: Member): Member = memberRepository.save(member)
}