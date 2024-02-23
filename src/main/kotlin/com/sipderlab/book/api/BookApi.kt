package com.sipderlab.book.api

import com.sipderlab.book.domain.request.BookRequest
import com.sipderlab.book.service.BookApiService
import com.sipderlab.common.domain.response.RestResult
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/book")
class BookApi(
    private val bookApiService: BookApiService
) {

    @PostMapping("/consignment")
    fun consignment(@Valid @RequestBody bookRequest: BookRequest): RestResult {
        bookApiService.bookConsignment(bookRequest)
        return RestResult.ok()
    }
}