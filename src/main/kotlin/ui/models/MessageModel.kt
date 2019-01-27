package ui.models

import org.apache.camel.Header
import tornadofx.*
import ui.events.MessageAddedEvent

data class KafkaMessage(val topic: String, val message: String)

class MessageModel : Component() {
    @Suppress("unused")
    fun add(@Header("kafka.TOPIC") topic: String, payload: String) {
        fire(MessageAddedEvent(KafkaMessage(topic, payload)))
    }
}