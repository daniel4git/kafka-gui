package ui.views

import beans.HighlightMessage
import javafx.scene.control.ListView
import javafx.scene.control.TextField
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.input.KeyEvent
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import javafx.scene.text.Font
import tornadofx.*
import ui.controllers.LogController
import utils.highlight

class LogPane : View("Log") {

    private val c: LogController by inject()

    var tfSearch: TextField by singleAssign()

    var lv: ListView<HighlightMessage> by singleAssign()

    override val root = vbox {
        lv = listview(c.messageList) {
            cellFormat {
                style {
                    fontFamily = "Menlo"
                }
                graphic = highlight(item.message, item.searchTerm, true)
            }
            vgrow = Priority.ALWAYS
        }
        tfSearch = textfield(c.searchField) {
            setOnKeyReleased {
                search()
            }
            managedProperty().bind(visibleProperty())
            isVisible = false
        }
        setOnKeyPressed {
            handleKeyPress(it)
        }
        paddingAll = 10.0
        spacing = 4.0
    }

    fun handleKeyPress(keyEvent: KeyEvent) {
        val findMac = KeyCodeCombination(KeyCode.F, KeyCombination.META_DOWN)
        if (findMac.match(keyEvent)) {
            tfSearch.isVisible = !tfSearch.isVisible
            tfSearch.requestFocus()
        } else if (keyEvent.code == KeyCode.ESCAPE) {
            tfSearch.isVisible = false
        }
        search()
    }

    fun search() {
        c.messageList.forEach {
            if (tfSearch.isVisible) {
                it.searchTerm = c.searchField.valueSafe
            } else {
                it.searchTerm = ""
            }
        }
        lv.refresh()
    }
}

