package com.msa.notification.adapter.`in`.messaging

import com.msa.notification.application.port.`in`.SendWelcomeEmailUseCase
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

// 수신된 UserCreatedEventEnvelope 객체로부터 UseCase가 올바르게 호출되는지 검증
class UserCreatedEventConsumerTest {
    private val sendWelcomeEmailUseCase: SendWelcomeEmailUseCase = mockk(relaxed = true)

    private lateinit var userCreatedEventConsumer: UserCreatedEventConsumer

    @BeforeEach
    fun setUp() {
        userCreatedEventConsumer = UserCreatedEventConsumer(sendWelcomeEmailUseCase)
    }

    @Test
    @DisplayName("메시지를 수신하면 파싱하여 UseCase로 커맨드 전달")
    fun handleUserCreated_success() {

        // Given
        val event = UserCreatedEventEnvelope(
            metadata = EventMetadata(
                eventId = "id-1234",
                eventType = "USER_CREATED",
                eventVersion = "v1",
                timestamp = "2026-11-24T17:40:00Z",
                producer = "user-service"
            ),
            payload = UserCreatedPayloadV1(
                userId = "user-id-001",
                name = "유장훈",
                email = "test@example.com"
            )
        )

        // When
        userCreatedEventConsumer.handleUserCreated(event)

        // Then
        verify(exactly = 1) {
            sendWelcomeEmailUseCase.sendWelcomeEmail(match {
                it.userId == "user-id-001" &&
                it.name == "유장훈" &&
                it.email == "test@example.com"
            })
        }
    } 
}
