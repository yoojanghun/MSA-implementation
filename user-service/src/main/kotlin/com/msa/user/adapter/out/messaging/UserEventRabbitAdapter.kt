package com.msa.user.adapter.out.messaging

import com.msa.user.application.port.out.PublishUserEventPort
import com.msa.user.domain.model.User
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

// rabbitMQ 전송 구현체(트랜잭션 커밋 이후 메세지 발행)
@Component
class UserEventRabbitAdapter(
    private val applicationEventPublisher: ApplicationEventPublisher
) : PublishUserEventPort {

    // UserCreatedEventMessage를 생성해 내부 이벤트 발행
    override fun publishUserCreated(user: User) {
        val message = UserCreatedEventMessage.from(user)
        applicationEventPublisher.publishEvent(message)
    }
}

// 트랜잭션 commit 후에 MQ로 전송
@Component
class UserCreatedRabbitListener(
    private val rabbitTemplate: RabbitTemplate
) {
    @TransactionalEventListener(
        phase = TransactionPhase.AFTER_COMMIT,
        fallbackExecution = true // 트랜잭션 매니저가 없는 단일 mongoDB 환경에서 MQ 전송
    )
    fun onUserCreated(message: UserCreatedEventMessage) {
        rabbitTemplate.convertAndSend("user.exchange", "user.created.v1", message)
    }
}
