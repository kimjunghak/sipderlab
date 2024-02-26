package com.sipderlab.book.service.data

import com.sipderlab.book.domain.entity.Book
import com.sipderlab.book.domain.state.BookStatus
import com.sipderlab.book.repository.BookRepository
import com.sipderlab.member.domain.entity.Member
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.given

@ExtendWith(MockitoExtension::class)
class BookDataWriteServiceTest {

    @InjectMocks
    private lateinit var bookDataWriteService: BookDataWriteService

    @Mock
    private lateinit var bookRepository: BookRepository

    @Test
    fun borrow_books_success() {
        val member = Member(1, "홍길동", "hong@woodo.kr", "010-0001-0002", "password12")
        val bookList = mutableListOf(
            Book(
                1L,
                "9791168473690",
                "세이노의 가르침",
                1500,
            ),
            Book(
                2L,
                "9791168473691",
                "세이노의 가르침 2",
                1500,
            ),
            Book(
                3L,
                "9791168473692",
                "세이노의 가르침 3",
                1500,
            )
        )
        bookList.forEach { it.member = member }

        val bookIds: List<Long> = listOf(1L, 2L)

        given(bookRepository.findAllByBookIdInAndStatus(bookIds, BookStatus.AVAILABLE)).willReturn(listOf(bookList[0], bookList[1]))

        bookDataWriteService.borrowBooks(bookIds)

        assertEquals(BookStatus.BORROWED, bookList[0].status)
        assertEquals(BookStatus.BORROWED, bookList[1].status)
        assertEquals(BookStatus.AVAILABLE, bookList[2].status)
    }

    @Test
    fun borrow_books_some_fail() {
        val bookIds = listOf(1L, 2L)

        given(bookRepository.findAllByBookIdInAndStatus(bookIds, BookStatus.AVAILABLE)).willReturn(listOf())

        val result = bookDataWriteService.borrowBooks(bookIds)

        assertEquals("Some books do not exist.", result)
    }
}