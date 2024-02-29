package com.example.kafkatracinglistener1

import io.opentelemetry.context.Context
import mu.KotlinLogging
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@SpringBootApplication
class KafkaTracingListener1Application

fun main(args: Array<String>) {
    runApplication<KafkaTracingListener1Application>(*args)
}

@Component
class BatchListener {

    var counter = 0

    @KafkaListener(topics = ["myTopic"])
    fun consume(events: List<ConsumerRecord<String, String>>) {

        val currentContext = Context.current()
        println(currentContext)
        logger.info { "consumed ${events.map { it.key() + "-" + it.value() }}" }
        counter++
        if (counter == 3) {
            logger.error { "Error!" }
            throw RuntimeException("sth went wrong")
        }
    }
}