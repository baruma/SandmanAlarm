package com.example.sandmanalarm

import java.util.*

object IdGenerator {
    fun create(): Long {
        return UUID.randomUUID().mostSignificantBits
    }
}
