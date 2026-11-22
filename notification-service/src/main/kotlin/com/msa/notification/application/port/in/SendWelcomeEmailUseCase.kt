package com.msa.notification.application.port.`in`

data class SendWelcomeEmailCommand(
    val userId: String,
    val name: String,
    val email: String
)

interface SendWelcomeEmailUseCase {
    fun sendWelcomeEmail(command: SendWelcomeEmailCommand)
}
