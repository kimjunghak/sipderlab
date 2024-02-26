package com.sipderlab.member.api

import com.sipderlab.common.domain.response.RestResponse
import com.sipderlab.member.domain.request.LoginRequest
import com.sipderlab.member.domain.request.MemberRequest
import com.sipderlab.member.service.MemberApiService
import jakarta.servlet.http.HttpSession
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/member")
class MemberApi(
    private val memberApiService: MemberApiService
) {

    @PostMapping("/signup")
    fun signup(@Valid @RequestBody memberRequest: MemberRequest): RestResponse {
        memberApiService.signup(memberRequest)
        return RestResponse.ok()
    }

    @PostMapping("/login")
    fun login(@Valid @RequestBody loginRequest: LoginRequest, session: HttpSession): RestResponse {
        memberApiService.login(loginRequest, session)
        return RestResponse.ok()
    }
}