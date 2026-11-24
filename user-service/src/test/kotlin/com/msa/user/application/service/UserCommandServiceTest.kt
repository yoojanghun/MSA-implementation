package com.msa.user.application.service

import com.msa.user.application.port.`in`.CreateUserCommand
import com.msa.user.application.port.out.PublishUserEventPort
import com.msa.user.application.port.out.SaveUserPort
import com.msa.user.domain.model.User
import io.mockk.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

// user 정보 DB 저장, MQ 발행
class UserCommandServiceTest {

    private val saveUserPort: SaveUserPort = mockk()
    private val publishUserEventPort: PublishUserEventPort = mockk(relaxed = true)

    private lateinit var userCommandService: UserCommandService

    @BeforeEach
    fun setup() {
        userCommandService = UserCommandService(saveUserPort, publishUserEventPort)
    }

    @Test
    @DisplayName("DB 저장 성공한 이후 RabbitMQ event 순서대로 발행")
    fun createUser_success_publishEventAfterDBSave() {
        
        // Given
        val command = CreateUserCommand(name = "유장훈", email = "test@example.com")
        every { saveUserPort.saveUser(any()) } answers { firstArg() }

        // When
        val result = userCommandService.createUser(command)

        // Then
        assertEquals("유장훈", result.name)
        assertEquals("test@example.com", result.email)

        verifyOrder {
            saveUserPort.saveUser(any())
            publishUserEventPort.publishUserCreated(result)
        }
    }

    @Test
    @DisplayName("DB 저장 실패 시 RabbitMQ event 발행 안 됨")
    fun createUser_dbFail_shouldNotPublishEvent() {

        // Given
        val command = CreateUserCommand(name = "유장훈", email = "test@example.com")
        every { saveUserPort.saveUser(any()) } throws RuntimeException("Database 오류")

        // When & Then
        assertThrows(RuntimeException::class.java) {
            userCommandService.createUser(command)
        }

        // MQ 메서드는 호출되지 말아야 함
        verify(exactly = 0) {
            publishUserEventPort.publishUserCreated(any())
        }
    }
}
