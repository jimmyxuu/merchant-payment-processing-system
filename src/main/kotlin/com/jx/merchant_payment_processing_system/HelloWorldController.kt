package com.jx.merchant_payment_processing_system

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController {

    @GetMapping
    fun helloWorld() = "Hello World"
}
