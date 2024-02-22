package com.sipderlab.member.domain.request

import com.sipderlab.member.domain.entity.Member
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

class MemberRequest(
    val userId: Long? = null,
    val name: String,
    val email: String,
    val cellNo: String,
    @Size(min = 6, max = 10, message = "Password must be between 6 and 10 characters")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).{2,}\$", message = "Password must contain at least one letter and one number")
    private val password: String,
) {

    fun requestToEntity(): Member = Member(
        userId = this.userId,
        name = this.name,
        email = this.email,
        cellNo = this.cellNo,
        password = this.password
    )
}