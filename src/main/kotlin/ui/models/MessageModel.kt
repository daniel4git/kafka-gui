package ui.models

import org.apache.camel.Header
import tornadofx.*
import ui.controllers.SettingsController
import utils.formatJson

class MessageAddedEvent(val message : HighlightMessage) : FXEvent(EventBus.RunOn.BackgroundThread)
class MessageChangedEvent : FXEvent(EventBus.RunOn.BackgroundThread)

/**
 * Create this with find() since it's a Component to get CDI
 */
class MessageModel : Component() {

    val settings_c : SettingsController by inject()

    fun add(@Header("kafka.TOPIC") topic: String, payload: String) {

        val doFormat = settings_c.formatJson.value

        val message = if (doFormat) {
            formatJson(payload)
        } else {
            payload
        }

        // background thread
        fire(MessageAddedEvent(HighlightMessage("$topic\n$message")))
    }
}