package com.msa.user.application.port.out

import com.msa.user.domain.model.User

interface SaveUserPort {
    fun saveUser(user: User): User
}
