package com.msa.user.application.port.out

import com.msa.user.domain.model.User

interface LoadUserPort {
    fun loadUser(id: String): User?
    fun loadAllUsers(): List<User>
}
