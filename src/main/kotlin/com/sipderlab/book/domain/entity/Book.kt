package com.sipderlab.book.domain.entity

import com.sipderlab.book.domain.state.BookStatus
import com.sipderlab.common.domain.entity.BaseEntity
import com.sipderlab.member.domain.entity.Member
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Book(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val bookId: Long? = null,
    val isbn: String,
    val name: String,
    val price: Int,
): BaseEntity() {

    var borrowCnt: Int = 0

    var borrowedAt: LocalDateTime? = null

    @Enumerated(EnumType.STRING)
    var status: BookStatus? = BookStatus.AVAILABLE

    @ManyToOne
    @JoinColumn(name = "memberId")
    lateinit var member: Member
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Book

        if (bookId != other.bookId) return false
        if (isbn != other.isbn) return false
        if (name != other.name) return false
        if (price != other.price) return false
        if (borrowCnt != other.borrowCnt) return false
        if (status != other.status) return false
        if (member != other.member) return false

        return true
    }

    override fun hashCode(): Int {
        var result = bookId?.hashCode() ?: 0
        result = 31 * result + isbn.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + price
        result = 31 * result + borrowCnt
        result = 31 * result + (status?.hashCode() ?: 0)
        result = 31 * result + member.hashCode()
        return result
    }


}