package com.jx.merchant_payment_processing_system.repository

import com.jx.merchant_payment_processing_system.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
}