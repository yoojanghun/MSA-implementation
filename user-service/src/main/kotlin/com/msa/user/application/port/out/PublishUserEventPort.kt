package com.msa.user.application.port.out

import com.msa.user.domain.model.User

interface PublishUserEventPort {
    fun publishUserCreated(user: User)
}
