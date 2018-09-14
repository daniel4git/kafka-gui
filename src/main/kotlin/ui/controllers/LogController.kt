package ui.controllers

import ui.models.HighlightMessage
import ui.models.MessageAddedEvent
import javafx.application.Platform
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.input.KeyEvent
import tornadofx.*
import ui.models.MessageChangedEvent
import ui.models.SearchOpenedEvent

class LogController : Controller() {

    val searchTerm = SimpleStringProperty()
    val isSearchVisible = SimpleBooleanProperty()
    val messageList = mutableListOf<HighlightMessage>().observable()

    init {
        subscribe<MessageAddedEvent> {
            Platform.runLater { messageList.add( it.message ) }
        }
    }

    fun handleKeyPress(keyEvent: KeyEvent) {
        val findMac = KeyCodeCombination(KeyCode.F, KeyCombination.META_DOWN)
        if (findMac.match(keyEvent)) {
            isSearchVisible.value = true
            fire(SearchOpenedEvent())
        } else if (keyEvent.code == KeyCode.ESCAPE) {
            isSearchVisible.value = false
        }
    }

    fun search() {
        messageList.forEach {
            if (isSearchVisible.value) {
                it.searchTerm = searchTerm.valueSafe
            } else {
                it.searchTerm = ""
            }
        }
        fire(MessageChangedEvent())
    }
}