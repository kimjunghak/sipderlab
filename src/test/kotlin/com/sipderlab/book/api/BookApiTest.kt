package com.sipderlab.book.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.sipderlab.book.domain.request.BookRequest
import com.sipderlab.common.domain.response.RestResult
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class BookApiTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun consignment_success() {
        val bookRequest = BookRequest("9791168473690", "세이노의 가르침", 1500)
        val json = objectMapper.writeValueAsString(bookRequest)

        val response = mockMvc.perform(
            post("/book/consignment")
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
    fun consignment_fail_isbn() {
        val bookRequest = BookRequest("979116847", "세이노의 가르침", 1500)
        val json = objectMapper.writeValueAsString(bookRequest)

        val response = mockMvc.perform(
            post("/book/consignment")
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
        assertEquals("ISBN must be 10 or 13 digits", data["isbn"])
    }
}