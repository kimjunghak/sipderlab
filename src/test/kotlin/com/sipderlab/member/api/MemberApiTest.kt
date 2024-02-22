package com.sipderlab.member.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.sipderlab.common.domain.response.RestResult
import com.sipderlab.member.domain.request.MemberRequest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class MemberApiTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

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

        val responseData = objectMapper.readValue(response, RestResult::class.java)
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

        val responseData = objectMapper.readValue(response, RestResult::class.java)
        assertEquals("Argument Not Valid", responseData.msg)
        val data = objectMapper.convertValue(responseData.data, Map::class.java)
        assertEquals("Password must be between 6 and 10 characters and contain at least one letter and one number", data["password"])
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

        val responseData = objectMapper.readValue(response, RestResult::class.java)
        assertEquals("Argument Not Valid", responseData.msg)
        val data = objectMapper.convertValue(responseData.data, Map::class.java)
        assertEquals("Password must be between 6 and 10 characters and contain at least one letter and one number", data["password"])
    }
}