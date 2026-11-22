package com.msa.notification.adapter.`in`.messaging

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class UserCreatedEventEnvelope(
    @JsonAlias("metaData")
    val metadata: EventMetadata = EventMetadata(),

    @JsonAlias("payLoad")
    val payload: UserCreatedPayloadV1
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class EventMetadata(
    val eventId: String = "",
    val eventType: String = "",
    val eventVersion: String = "",
    val timestamp: String = "",
    val producer: String = ""
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class UserCreatedPayloadV1(
    val userId: String = "",
    val name: String = "",
    val email: String = ""
)
