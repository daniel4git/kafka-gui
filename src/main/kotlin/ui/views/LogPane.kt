package ui.views

import javafx.scene.layout.Priority
import tornadofx.*
import ui.controllers.LogController
import ui.models.MessageChangedEvent
import utils.highlight

class LogPane : View("Log") {

    private val c: LogController by inject()

    override val root = vbox {
        listview(c.messageList) {
            subscribe<MessageChangedEvent> {
                refresh()
            }
            cellFormat {
                style {
                    fontFamily = "Menlo"
                }
                graphic = highlight(item.message, item.searchTerm, true)
            }
            vgrow = Priority.ALWAYS
        }

        textfield(c.searchField) {
            setOnKeyReleased { c.search() }
            managedProperty().bind(visibleProperty())
            visibleWhen(c.searchIsVisible)
        }

        setOnKeyPressed {
            c.handleKeyPress(it)
        }

        paddingAll = 10.0
        spacing = 4.0
    }
}

