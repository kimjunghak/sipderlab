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
    fun createMember(member: Member): Member = memberRepository.save(member)

    //테스트 데이터 입력 전용
    fun saveAllMember(members: List<Member>): List<Member> = memberRepository.saveAll(members)
}