package ui.views

import javafx.scene.control.TextField
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.input.KeyEvent
import javafx.scene.layout.StackPane
import tornadofx.*

class LogPane : View("Log") {
    override val root: StackPane by fxml("/views/LogPane.fxml")

    private val searchField: TextField by fxid()

    fun handleKeyPress(keyEvent: KeyEvent) {
        val findMac = KeyCodeCombination(KeyCode.F, KeyCombination.META_DOWN)
        if (findMac.match(keyEvent)) {
            searchField.isVisible = !searchField.isVisible
            searchField.requestFocus()
        } else if (keyEvent.code == KeyCode.ESCAPE) {
            searchField.isVisible = false
        }
    }

    fun search() {

    }
}