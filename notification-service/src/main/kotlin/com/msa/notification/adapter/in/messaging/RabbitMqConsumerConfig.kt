package com.msa.notification.adapter.`in`.messaging

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMqConsumerConfig {

    companion object {
        const val EXCHANGE_NAME = "user.exchange"
        const val QUEUE_NAME = "notification.user.created.queue"
        const val ROUTING_KEY = "user.created.v1"
    }

    @Bean
    fun userExchange(): TopicExchange = TopicExchange(EXCHANGE_NAME, true, false)

    @Bean
    fun notificationQueue(): Queue = Queue(QUEUE_NAME, true)

    @Bean
    fun binding(notificationQueue: Queue, userExchange: TopicExchange): Binding =
        BindingBuilder.bind(notificationQueue).to(userExchange).with(ROUTING_KEY)

    @Bean
    fun jackson2JsonMessageConverter(): MessageConverter = Jackson2JsonMessageConverter()
}
