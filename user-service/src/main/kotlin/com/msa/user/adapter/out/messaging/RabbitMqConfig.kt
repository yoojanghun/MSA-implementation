package com.msa.user.adapter.out.messaging

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

// publish직전/consume 직전 각각 직렬화/역직렬화 시 JSON 바이트 배열로 
@Configuration
class RabbitMqConfig {
    @Bean
    fun messageConverter(): MessageConverter {
        return Jackson2JsonMessageConverter()
    }
}
