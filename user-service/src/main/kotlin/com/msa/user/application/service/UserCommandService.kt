package com.msa.user.application.service

import com.msa.user.application.port.`in`.CreateUserCommand
import com.msa.user.application.port.`in`.CreateUserUseCase
import com.msa.user.application.port.out.PublishUserEventPort
import com.msa.user.application.port.out.SaveUserPort
import com.msa.user.domain.model.User
import org.springframework.stereotype.Service

@Service
class UserCommandService(
    private val saveUserPort: SaveUserPort,
    private val publishUserEventPort: PublishUserEventPort
) : CreateUserUseCase {

    override fun createUser(command: CreateUserCommand): User {
        val newUser = User.create(name = command.name, email = command.email)
        val savedUser = saveUserPort.saveUser(newUser)
        publishUserEventPort.publishUserCreated(savedUser)
        return savedUser
    }
}
