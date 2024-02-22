package com.sipderlab.member.api

import com.sipderlab.common.domain.response.RestResult
import com.sipderlab.member.domain.request.MemberRequest
import com.sipderlab.member.service.MemberApiService
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
    fun signup(@Valid @RequestBody memberRequest: MemberRequest): RestResult {
        memberApiService.signup(memberRequest)
        return RestResult.ok()
    }
}