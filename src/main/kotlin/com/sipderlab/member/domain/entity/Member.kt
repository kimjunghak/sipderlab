package com.sipderlab.member.domain.entity

import com.sipderlab.common.domain.entity.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Member(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val memberId: Long? = null,
    val name: String,
    val email: String,
    val cellNo: String,
    var password: String,
): BaseEntity()
{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Member

        if (memberId != other.memberId) return false
        if (name != other.name) return false
        if (email != other.email) return false
        if (cellNo != other.cellNo) return false
        if (password != other.password) return false

        return true
    }

    override fun hashCode(): Int {
        var result = memberId?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + cellNo.hashCode()
        result = 31 * result + password.hashCode()
        return result
    }
}