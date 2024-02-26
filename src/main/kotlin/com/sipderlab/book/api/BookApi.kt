package com.sipderlab.book.api

import com.sipderlab.book.domain.request.BookRequest
import com.sipderlab.book.domain.request.BorrowRequest
import com.sipderlab.book.domain.response.BookResponse
import com.sipderlab.book.service.BookApiService
import com.sipderlab.common.domain.response.PageResponse
import com.sipderlab.common.domain.response.RestResponse
import com.sipderlab.common.exception.UnAuthorizedException
import jakarta.servlet.http.HttpSession
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/book")
class BookApi(
    private val bookApiService: BookApiService
) {

    @PostMapping("/consignment")
    fun consignment(@Valid @RequestBody bookRequest: BookRequest, session: HttpSession): RestResponse {
        val loginMemberId = session.getAttribute("session") as? Long ?: throw UnAuthorizedException("Please login first.")
        bookApiService.consignmentBook(bookRequest, loginMemberId)
        return RestResponse.ok()
    }

    @GetMapping("/list")
    fun getBooks(@PageableDefault(sort = ["borrowCnt"], direction = Sort.Direction.DESC) pageable: Pageable): RestResponse {
        val books: PageResponse<BookResponse> = bookApiService.getBooks(pageable)
        return RestResponse.ok(books)
    }

    @PostMapping("/borrow")
    fun borrow(@RequestBody borrowRequest: BorrowRequest): RestResponse {
        val result = bookApiService.borrowBooks(borrowRequest.bookIds)
        val data = if(result != null) mapOf(Pair("additionalInfo", result)) else null
        return RestResponse.ok(data)
    }
}