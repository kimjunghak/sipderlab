package com.sipderlab.member.service

import com.sipderlab.common.exception.SpiderlabException
import com.sipderlab.common.exception.UnAuthorizedException
import com.sipderlab.member.domain.crypt.SimplePasswordEncoder
import com.sipderlab.member.domain.entity.Member
import com.sipderlab.member.domain.request.LoginRequest
import com.sipderlab.member.domain.request.MemberRequest
import com.sipderlab.member.service.data.MemberDataReadService
import com.sipderlab.member.service.data.MemberDataWriteService
import jakarta.servlet.http.HttpSession
import org.springframework.stereotype.Service
import kotlin.math.log

@Service
class MemberApiService(
    private val memberDataReadService: MemberDataReadService,
    private val memberDataWriteService: MemberDataWriteService,
    private val passwordEncoder: SimplePasswordEncoder,
) {

    fun signup(memberRequest: MemberRequest) {
        checkDuplicateEmail(memberRequest.email)

        val newMember: Member = memberRequest.toMember()
        val rawPassword = memberRequest.password
        newMember.password = passwordEncoder.encodePassword(rawPassword)!!
        memberDataWriteService.createMember(newMember)
    }

    private fun checkDuplicateEmail(name: String) {
        if (memberDataReadService.existsByEmail(name)) {
            throw SpiderlabException("Email already exists. Please try another email.")
        }
    }

    fun login(loginRequest: LoginRequest, session: HttpSession) {
        val member: Member = memberDataReadService.findByEmail(loginRequest)
            .orElseThrow { UnAuthorizedException("Password or email does not match.") }
        checkPassword(loginRequest.password, member.password)
        session.setAttribute("session", member.memberId)
    }

    private fun checkPassword(
        loginPassword: String,
        memberPassword: String
    ) {
        if (!passwordEncoder.matches(loginPassword, memberPassword)) {
            throw UnAuthorizedException("Password or email does not match.")
        }
    }
}