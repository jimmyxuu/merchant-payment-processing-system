package com.jx.merchant_payment_processing_system.controller

import com.jx.merchant_payment_processing_system.dto.UserResponse
import com.jx.merchant_payment_processing_system.service.UserService
import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @PostMapping("/register")
    fun register(@Valid @RequestBody request: RegisterUserRequest): UserResponse {
        return userService.registerUser(request)
    }
}

data class RegisterUserRequest(
    @field:NotBlank
    val name: String,

    @field:NotBlank
    @field:Email
    val email: String,

    @field:NotBlank
    @field:Size(min = 8, max = 64)
    val password: String
)