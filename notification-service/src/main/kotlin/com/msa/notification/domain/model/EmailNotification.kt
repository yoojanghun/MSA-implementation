package com.msa.notification.domain.model

data class EmailNotification(
    val userId: String,
    val name: String,
    val email: String
)
