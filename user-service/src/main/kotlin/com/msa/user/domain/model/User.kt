package com.msa.user.domain.model

import java.util.UUID

data class User(
    val id: String,
    val name: String,
    val email: String
) {
    companion object {
        fun create(name: String, email: String): User {
            require(name.isNotBlank()) { "Name must not be blank" }
            require(email.isNotBlank()) { "Email must not be blank" }

            return User(
                id = UUID.randomUUID().toString(),
                name = name,
                email = email
            )
        }
    }
}
