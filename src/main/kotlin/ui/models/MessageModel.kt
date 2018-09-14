package ui.models

import org.apache.camel.Header
import tornadofx.*
import ui.controllers.SettingsController
import utils.formatJson

class MessageAddedEvent(val message : HighlightMessage) : FXEvent(EventBus.RunOn.BackgroundThread)
class MessageChangedEvent : FXEvent(EventBus.RunOn.BackgroundThread)

class MessageModel : Component() {

    private val settings : SettingsController by inject()

    @Suppress("unused")
    fun add(@Header("kafka.TOPIC") topic: String, payload: String) {

        val doFormat = settings.formatJson.value

        val message = if (doFormat) {
            formatJson(payload)
        } else {
            payload
        }

        fire(MessageAddedEvent(HighlightMessage("$topic\n$message")))
    }
}