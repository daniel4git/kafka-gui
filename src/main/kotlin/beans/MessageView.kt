package beans

import javafx.application.Platform
import tornadofx.*
import ui.views.HighlightMessage
import ui.views.LogPane
import ui.views.SettingsPane
import utils.formatJson

class MessageView {
    fun add(topic: String, payload: String) {
        Platform.runLater {
            val doFormat = find(SettingsPane::class).formatJson.isSelected
            val messageList = find(LogPane::class).messageList

            val message = if (doFormat) {
                formatJson(payload)
            } else {
                payload
            }

            messageList.items.add(HighlightMessage("$topic\n$message"))
            messageList.scrollTo(messageList.items.size - 1)
        }
    }
}