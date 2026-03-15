package com.jx.merchant_payment_processing_system.dto

import java.time.LocalDateTime

data class UserResponse(
    val id: Long,
    val name: String,
    val email: String,
    val createdAt: LocalDateTime
)
