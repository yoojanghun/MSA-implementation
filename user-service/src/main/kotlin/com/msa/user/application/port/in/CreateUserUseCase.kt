package com.msa.user.application.port.`in`

import com.msa.user.domain.model.User

interface CreateUserUseCase {
    fun createUser(command: CreateUserCommand): User
}

data class CreateUserCommand(
    val name: String,
    val email: String
)
