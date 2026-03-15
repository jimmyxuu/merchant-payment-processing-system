package com.jx.merchant_payment_processing_system.service

import com.jx.merchant_payment_processing_system.controller.RegisterUserRequest
import com.jx.merchant_payment_processing_system.dto.UserResponse
import com.jx.merchant_payment_processing_system.entity.User
import com.jx.merchant_payment_processing_system.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun registerUser(registerUserRequest: RegisterUserRequest): UserResponse {
        if (userRepository.findByEmail(registerUserRequest.email) != null) {
            throw RuntimeException("User email already registered")
        }

        val encodedPassword = passwordEncoder.encode(registerUserRequest.password)!! // passwordEncoder returns nullable string

        val newUser = User(
            name = registerUserRequest.name,
            email = registerUserRequest.email,
            passwordHash = encodedPassword,
            createdAt = LocalDateTime.now()
        )

        val savedUser = userRepository.save(newUser)

        return UserResponse(
            id = savedUser.id!!,
            name = savedUser.name,
            email = savedUser.email,
            createdAt = savedUser.createdAt
        )
    }

}
