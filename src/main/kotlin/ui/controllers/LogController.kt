package ui.controllers

import beans.HighlightMessage
import ui.models.MessageAddedEvent
import javafx.application.Platform
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class LogController : Controller() {

    val searchField = SimpleStringProperty()

    val messageList = mutableListOf<HighlightMessage>().observable()

    init {
        subscribe<MessageAddedEvent> {
            Platform.runLater {
                messageList.add( it.message )
            }
        }
    }
}