package com.msa.notification.adapter.`in`.messaging

import com.msa.notification.application.port.`in`.SendWelcomeEmailCommand
import com.msa.notification.application.port.`in`.SendWelcomeEmailUseCase
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class UserCreatedEventConsumer(
    private val sendWelcomeEmailUseCase: SendWelcomeEmailUseCase
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @RabbitListener(queues = [RabbitMqConsumerConfig.QUEUE_NAME])
    fun handleUserCreated(event: UserCreatedEventEnvelope) {
        log.info("유저 가입 이벤트 수신: eventId={}", event.metadata.eventId)

        val command = SendWelcomeEmailCommand(
            userId = event.payload.userId,
            name = event.payload.name,
            email = event.payload.email
        )
        sendWelcomeEmailUseCase.sendWelcomeEmail(command)
    }
}
