package com.msa.user.adapter.out.messaging

import com.msa.user.domain.model.User
import java.time.Instant
import java.util.UUID

// 전송할 JSON 데이터 규격 정의
data class UserCreatedEventMessage(
    val metaData: EventMetadata,
    val payLoad: UserCreatedPayload
) {
    data class EventMetadata(
        val eventId: String = UUID.randomUUID().toString(), // 멱등성 보장(중복 처리 방지)
        val eventType: String = "USER_CREATED",
        val eventVersion: String = "v1",
        val timestamp: String = Instant.now().toString(),
        val producer: String = "user-service"
    )

    data class UserCreatedPayload(
        val userId: String,
        val name: String,
        val email: String
    )

    companion object {
        fun from(user: User): UserCreatedEventMessage {
            return UserCreatedEventMessage(
                metaData = EventMetadata(),
                payLoad = UserCreatedPayload(
                    userId = user.id,
                    name = user.name,
                    email = user.email
                )
            )
        }
    }
}
