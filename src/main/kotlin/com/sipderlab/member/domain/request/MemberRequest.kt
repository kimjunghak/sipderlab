package com.sipderlab.member.domain.request

import com.sipderlab.member.domain.entity.Member
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

class MemberRequest(
    @field:NotNull(message = "Name is required")
    val name: String,
    @field:NotNull(message = "Email is required")
    @field:Email(message = "Email format is not valid")
    val email: String,
    @field:NotNull(message = "Cell number is required")
    val cellNo: String,
    @field:NotNull(message = "Password is required")
    @field:Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).{2,}\$", message = "Contain at least one letter and one number")
    @field:Size(min = 6, max = 10, message = "Password must be between 6 and 10 characters")
    val password: String,
) {

    fun toMember(): Member = Member(
        name = this.name,
        email = this.email,
        cellNo = this.cellNo,
        password = this.password
    )

    override fun toString(): String {
        return "MemberRequest(name='$name', email='$email', cellNo='$cellNo', password='$password')"
    }
}