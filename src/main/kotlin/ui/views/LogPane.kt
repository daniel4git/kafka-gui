package ui.views

import javafx.application.Platform
import javafx.scene.layout.Priority
import tornadofx.*
import ui.controllers.LogController
import ui.controllers.SearchOpenedEvent

class LogPane : View("Log") {

    private val controller: LogController by inject()
    private val filteredMessages = SortedFilteredList(controller.messageList)

    override val root = vbox {
        listview(filteredMessages) {
            cellFormat {
                graphic = MessageCell(it).root
            }
            vgrow = Priority.ALWAYS
        }

        textfield(controller.searchTerm) {
            subscribe<SearchOpenedEvent> {
                Platform.runLater { requestFocus() }
            }
            filteredMessages.filterWhen(textProperty()) { query, item ->
                item.message.contains(query, true) ||
                item.topic.contains(query, true)
            }
            managedProperty().bind(visibleProperty())
            visibleWhen(controller.isSearchVisible)
        }

        setOnKeyPressed {
            controller.handleKeyPress(it)
        }

        paddingAll = 10.0
        spacing = 4.0
    }
}

