package com.msa.user.adapter.out.persistence

import com.msa.user.domain.model.User
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
data class UserDocument(
    @Id
    val id: String,
    val name: String,
    val email: String
) {

    fun toDomain(): User = User(
        id = id,
        name = name,
        email = email
    )

    companion object {
        fun from(user: User): UserDocument = UserDocument(
            id = user.id,
            name = user.name,
            email = user.email
        )
    }
}
