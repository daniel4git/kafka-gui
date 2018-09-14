package ui.models

import org.apache.camel.Header
import tornadofx.*

data class KafkaMessage(val topic: String, val message: String)
class MessageAddedEvent(val message: KafkaMessage) : FXEvent(EventBus.RunOn.BackgroundThread)

class MessageModel : Component() {
    @Suppress("unused")
    fun add(@Header("kafka.TOPIC") topic: String, payload: String) {
        fire(MessageAddedEvent(KafkaMessage(topic, payload)))
    }
}