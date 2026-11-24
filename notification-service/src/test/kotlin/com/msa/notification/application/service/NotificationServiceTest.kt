package com.msa.notification.application.service

import com.msa.notification.application.port.`in`.SendWelcomeEmailCommand
import com.msa.notification.application.port.out.SendEmailPort
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

// UseCase 요청 시 SendEmailPort로 올바른 정보가 전달
class NotificationServiceTest {
    private val sendEmailPort: SendEmailPort = mockk(relaxed = true)

    private lateinit var notificationService: NotificationService

    @BeforeEach
    fun setUp() {
        notificationService = NotificationService(sendEmailPort)
    }

    @Test
    @DisplayName("SendWelcomeEmailCommand를 받으면 send 호출")
    fun sendWelcomeEmail_success() {

        // Given
        val command = SendWelcomeEmailCommand(
            userId = "user-1234", 
            name = "유장훈", 
            email = "test@example.com"
        )

        // When
        notificationService.sendWelcomeEmail(command)

        // Then
        verify(exactly = 1) {
            sendEmailPort.send(match {
                it.userId == "user-1234" && it.name == "유장훈" && it.email == "test@example.com"
            })
        }
    }
}
