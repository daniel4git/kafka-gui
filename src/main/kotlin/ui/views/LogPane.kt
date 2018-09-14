package ui.views

import javafx.application.Platform
import javafx.scene.layout.Priority
import tornadofx.*
import ui.controllers.LogController
import ui.models.MessageChangedEvent
import ui.models.SearchOpenedEvent
import utils.highlight

class LogPane : View("Log") {

    private val controller: LogController by inject()

    override val root = vbox {
        listview(controller.messageList) {
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

        textfield(controller.searchField) {
            subscribe<SearchOpenedEvent> {
                Platform.runLater { requestFocus() }
            }
            setOnKeyReleased { controller.search() }
            managedProperty().bind(visibleProperty())
            visibleWhen(controller.searchIsVisible)
        }

        setOnKeyPressed {
            controller.handleKeyPress(it)
        }

        paddingAll = 10.0
        spacing = 4.0
    }
}

