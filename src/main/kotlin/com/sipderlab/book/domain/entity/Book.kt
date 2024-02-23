package com.sipderlab.book.domain.entity

import com.sipderlab.common.domain.entity.BaseEntity
import jakarta.persistence.*

@Entity
class Book(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val bookId: Long? = null,
    @Column(unique = true, nullable = false)
    var isbn: String,
    var name: String,
    var price: Int,
    var count: Int = 1,
): BaseEntity() {

    fun countInc() {
        this.count += 1
    }
}