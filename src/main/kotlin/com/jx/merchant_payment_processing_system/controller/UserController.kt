package com.jx.merchant_payment_processing_system.controller

import com.jx.merchant_payment_processing_system.service.UserService
import com.jx.merchant_payment_processing_system.entity.User
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): User {
        return userService.registerUser(request)
    }
}

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)