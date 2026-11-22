package com.msa.user.adapter.out.persistence

import com.msa.user.domain.model.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @Column(name = "id", length = 36, nullable = false)
    val id: String,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "email", nullable = false)
    val email: String
) {
    fun toDomain(): User = User(
        id = id,
        name = name,
        email = email
    )

    companion object {
        fun from(user: User): UserEntity = UserEntity(
            id = user.id,
            name = user.name,
            email = user.email
        )
    }
}
