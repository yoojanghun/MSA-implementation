package com.msa.notification.application.service

import com.msa.notification.application.port.`in`.SendWelcomeEmailCommand
import com.msa.notification.application.port.`in`.SendWelcomeEmailUseCase
import com.msa.notification.application.port.out.SendEmailPort
import com.msa.notification.domain.model.EmailNotification
import org.springframework.stereotype.Service

@Service
class NotificationService(
    private val sendEmailPort: SendEmailPort
) : SendWelcomeEmailUseCase {

    override fun sendWelcomeEmail(command: SendWelcomeEmailCommand) {
        val emailNotification = EmailNotification(
            userId = command.userId,
            name = command.name,
            email = command.email
        )

        sendEmailPort.send(emailNotification)
    }
}
