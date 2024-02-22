package com.sipderlab.member.service

import com.sipderlab.member.domain.entity.Member
import com.sipderlab.member.domain.request.MemberRequest
import com.sipderlab.member.service.data.MemberDataReadService
import com.sipderlab.member.service.data.MemberDataWriteService
import org.springframework.stereotype.Service

@Service
class MemberApiService(
    private val memberDataReadService: MemberDataReadService,
    private val memberDataWriteService: MemberDataWriteService,
) {

    fun signup(memberRequest: MemberRequest) {
        checkDuplicateName(memberRequest.name)

        val newMember: Member = memberRequest.requestToEntity()
        memberDataWriteService.saveMember(newMember)
    }

    private fun checkDuplicateName(name: String) {
        if (memberDataReadService.existsByName(name)) {
            throw RuntimeException("Name already exists. Please try another name.")
        }
    }
}