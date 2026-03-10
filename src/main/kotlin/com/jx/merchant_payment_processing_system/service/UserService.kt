package com.jx.merchant_payment_processing_system.service

import com.jx.merchant_payment_processing_system.entity.User
import com.jx.merchant_payment_processing_system.repository.UserRepository
import com.jx.merchant_payment_processing_system.controller.RegisterRequest
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserService(private val userRepository: UserRepository) {

    fun registerUser(registerRequest: RegisterRequest): User {
        if (userRepository.findByEmail(registerRequest.email) != null) {
            throw RuntimeException("User email already registered")
        }

        val newUser = User(
            name = registerRequest.name,
            email = registerRequest.email,
            passwordHash = registerRequest.password,
            createdAt = LocalDateTime.now()
        )

        return userRepository.save(newUser)
    }

}
