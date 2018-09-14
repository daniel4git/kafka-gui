package ui.views

import tornadofx.*
import ui.controllers.LogController
import ui.controllers.SettingsController
import ui.models.KafkaMessage
import utils.formatJson
import utils.highlight

// TODO this should be a ListCellFragment
class MessageCell(message: KafkaMessage) : View("Message") {

    private val controller: LogController by inject()
    private val settings: SettingsController by inject()

    override val root = vbox {
        label {
            graphic = highlight(message.topic, controller.searchTerm.valueSafe)
            style { fontFamily = "Menlo" }
        }
        label {
            // TODO shouldn't have logic in a UI component
            val formattedMessage = if (settings.formatJson.value) {
                formatJson(message.message)
            } else {
                message.message
            }
            graphic = highlight(formattedMessage, controller.searchTerm.valueSafe)
            style { fontFamily = "Menlo" }
        }
    }
}