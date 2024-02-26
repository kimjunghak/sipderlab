package com.sipderlab.member.domain.request

import jakarta.validation.constraints.NotNull

data class LoginRequest(
    @NotNull(message = "Email must not be null")
    val email: String,
    @NotNull(message = "Password must not be null")
    val password: String,
)