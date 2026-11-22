package com.msa.user.adapter.out.persistence

import com.msa.user.application.port.out.LoadUserPort
import com.msa.user.application.port.out.SaveUserPort
import com.msa.user.domain.model.User
import org.springframework.stereotype.Component

@Component
class UserPersistenceAdapter(
    private val userMongoRepository: UserMongoRepository
) : SaveUserPort, LoadUserPort {

    override fun saveUser(user: User): User {
        val userDocument = UserDocument.from(user)
        val savedUserDocument = userMongoRepository.save(userDocument)
        return savedUserDocument.toDomain()
    }

    override fun loadUser(id: String): User? {
        return userMongoRepository.findById(id)
            .map { it.toDomain() }
            .orElse(null)
    }

    override fun loadAllUsers(): List<User> {
        return userMongoRepository.findAll()
            .map { it.toDomain() }
    }
}
