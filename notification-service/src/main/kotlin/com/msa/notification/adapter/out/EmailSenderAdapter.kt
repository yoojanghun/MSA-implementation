package com.msa.notification.adapter.out

import com.msa.notification.application.port.out.SendEmailPort
import com.msa.notification.domain.model.EmailNotification
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class EmailSenderAdapter : SendEmailPort {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun send(notification: EmailNotification) {
        log.info(
            "이메일 발송 완료 - userId={}, name={}, email={}",
            notification.userId,
            notification.name,
            notification.email
        )
    }
}
