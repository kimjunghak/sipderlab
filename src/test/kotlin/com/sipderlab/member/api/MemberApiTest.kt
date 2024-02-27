package com.sipderlab.member.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.sipderlab.common.domain.response.RestResponse
import com.sipderlab.member.domain.request.MemberRequest
import com.sipderlab.member.service.MemberApiService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(MockitoExtension::class)
@WebMvcTest(MemberApi::class)
@AutoConfigureMockMvc
class MemberApiTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var memberApiService: MemberApiService

    @Test
    fun signup_success() {
        val memberRequest = MemberRequest("홍길동", "hong@woodo.kr", "010-0001-0002", "password12")
        val json = objectMapper.writeValueAsString(memberRequest)
        val response = mockMvc.perform(
            post("/member/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
            .andExpect(status().isOk)
            .andDo(print())
            .andReturn()
            .response
            .contentAsString

        val responseData = objectMapper.readValue(response, RestResponse::class.java)
        assertEquals("success", responseData.msg)
    }

    @Test
    fun signup_fail_password_not_enough_number() {
        val memberRequest = MemberRequest("홍길동", "hong@woodo.kr", "010-0001-0002", "password")
        val json = objectMapper.writeValueAsString(memberRequest)

        val response = mockMvc.perform(
            post("/member/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
            .andExpect(status().isBadRequest)
            .andDo(print())
            .andReturn()
            .response
            .contentAsString

        val responseData = objectMapper.readValue(response, RestResponse::class.java)
        assertEquals("Argument Not Valid", responseData.msg)
        val data = objectMapper.convertValue(responseData.data, Map::class.java)
        assertEquals("Contain at least one letter and one number", data["password"])
    }

    @Test
    fun signup_fail_password_not_enough_length() {
        val memberRequest = MemberRequest("홍길동", "hong@woodo.kr", "010-0001-0002", "fdsa")
        val json = objectMapper.writeValueAsString(memberRequest)

        val response = mockMvc.perform(
            post("/member/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
            .andExpect(status().isBadRequest)
            .andDo(print())
            .andReturn()
            .response
            .contentAsString

        val responseData = objectMapper.readValue(response, RestResponse::class.java)
        assertEquals("Argument Not Valid", responseData.msg)
        val data = objectMapper.convertValue(responseData.data, Map::class.java)
        assertEquals("Password must be between 6 and 10 characters", data["password"])
    }
}