package com.msa.user.adapter.`in`

import com.msa.user.application.port.`in`.CreateUserCommand
import com.msa.user.application.port.`in`.CreateUserUseCase
import com.msa.user.application.port.out.LoadUserPort
import com.msa.user.domain.model.User
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class UserGraphqlController(
    private val createUserUseCase: CreateUserUseCase,
    private val loadUserPort: LoadUserPort
) {

    @MutationMapping
    fun createUser(@Argument input: CreateUserInput): UserResponse {
        val command = CreateUserCommand(
            name = input.name,
            email = input.email
        )
        val createdUser = createUserUseCase.createUser(command)
        return UserResponse.from(createdUser)
    }

    @QueryMapping
    fun findUser(@Argument id: String): UserResponse? {
        return loadUserPort.loadUser(id)?.let { UserResponse.from(it) }
    }

    @QueryMapping
    fun findAllUsers(): List<UserResponse> {
        return loadUserPort.loadAllUsers().map { UserResponse.from(it) }
    }
}

data class CreateUserInput(
    val name: String,
    val email: String
)

data class UserResponse(
    val id: String,
    val name: String,
    val email: String
) {
    companion object {
        fun from(user: User): UserResponse = UserResponse(
            id = user.id,
            name = user.name,
            email = user.email
        )
    }
}
