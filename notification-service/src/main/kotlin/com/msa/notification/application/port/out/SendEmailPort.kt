package com.msa.notification.application.port.out

import com.msa.notification.domain.model.EmailNotification

package com.msa.notification.application.port.out

import com.msa.notification.domain.model.EmailNotification

interface SendEmailPort {
    fun send(notification: EmailNotification)
}