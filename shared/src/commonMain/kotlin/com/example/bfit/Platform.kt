package com.example.bfit

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform