package com.msa.user.adapter.out.persistence

import com.msa.user.application.port.out.LoadUserPort
import com.msa.user.application.port.out.SaveUserPort
import com.msa.user.domain.model.User
import org.springframework.stereotype.Component

@Component
class UserPersistenceAdapter(
    private val userRepository: UserRepository
) : SaveUserPort, LoadUserPort {

    override fun saveUser(user: User): User {
        val entity = UserEntity.from(user)
        val savedEntity = userRepository.save(entity)
        return savedEntity.toDomain()
    }

    override fun loadUser(id: String): User? {
        return userRepository.findById(id)
            .map { it.toDomain() }
            .orElse(null)
    }

    override fun loadAllUsers(): List<User> {
        return userRepository.findAll()
            .map { it.toDomain() }
        }
}
