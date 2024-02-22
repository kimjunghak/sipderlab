package com.sipderlab.member.service

import com.sipderlab.member.domain.request.MemberRequest
import com.sipderlab.member.service.data.MemberDataReadService
import com.sipderlab.member.service.data.MemberDataWriteService
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any

@ExtendWith(MockitoExtension::class)
class MemberApiServiceTest {

    @InjectMocks
    private lateinit var memberApiService: MemberApiService

    @Mock
    private lateinit var memberDataReadService: MemberDataReadService

    @Mock
    private lateinit var memberDataWriteService: MemberDataWriteService

    @Test
    fun signup_success() {
        // given
        val memberRequest = MemberRequest(null, "홍길동", "hong@woodo.kr", "010-0001-0002", "password123")
        val newMember = memberRequest.requestToEntity()

        given(memberDataReadService.existsByName("홍길동")).willReturn(false)
        given(memberDataWriteService.saveMember(any())).willReturn(newMember)

        // when
        memberApiService.signup(memberRequest)

        // then
        // no exception
    }

    @Test
    fun signup_fail_already_exist_name() {
        // given
        val memberRequest = MemberRequest(null, "홍길동", "hong@woodo.kr", "010-0001-0002", "password123")
        val newMember = memberRequest.requestToEntity()

        given(memberDataReadService.existsByName("홍길동")).willReturn(true)

        // when
        val throws = assertThrows<RuntimeException> {
            memberApiService.signup(memberRequest)
        }

        // then
        assertEquals("Name already exists. Please try another name.", throws.message)
    }
}