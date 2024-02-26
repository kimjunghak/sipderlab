package com.sipderlab.book.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.sipderlab.book.domain.request.BookRequest
import com.sipderlab.book.service.BookApiService
import com.sipderlab.common.domain.response.RestResponse
import com.sipderlab.member.domain.entity.Member
import com.sipderlab.member.service.data.MemberDataReadService
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpSession
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.Optional

@WebMvcTest(BookApi::class)
@ExtendWith(MockitoExtension::class)
@AutoConfigureMockMvc
class BookApiTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var bookApiService: BookApiService

    @Mock
    private lateinit var memberDataReadService: MemberDataReadService
    @Test
    fun consignment_success() {
        val bookRequest = BookRequest("9791168473690", "세이노의 가르침", 1500)
        val json = objectMapper.writeValueAsString(bookRequest)

        val session = MockHttpSession().apply {
            setAttribute(
                "session",
                1L
            )
        }

        val response = mockMvc.perform(
            post("/book/consignment")
                .session(session)
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
    fun consignment_fail_not_login() {
        val bookRequest = BookRequest("9791168473690", "세이노의 가르침", 1500)
        val json = objectMapper.writeValueAsString(bookRequest)

        val response = mockMvc.perform(
            post("/book/consignment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
            .andExpect(status().isUnauthorized)
            .andDo(print())
            .andReturn()
            .response
            .contentAsString

        val responseData = objectMapper.readValue(response, RestResponse::class.java)
        assertEquals("Please login first.", responseData.msg)
    }

    @Test
    fun consignment_fail_isbn() {
        val bookRequest = BookRequest("979116847", "세이노의 가르침", 1500)
        val json = objectMapper.writeValueAsString(bookRequest)

        val session = MockHttpSession().apply {
            setAttribute(
                "session",
                Member(1, "홍길동", "hong@woodo.kr", "010-0001-0002", "password12")
            )
        }

        val response = mockMvc.perform(
            post("/book/consignment")
                .session(session)
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
        assertEquals("ISBN must be 10 or 13 digits", data["isbn"])
    }
}