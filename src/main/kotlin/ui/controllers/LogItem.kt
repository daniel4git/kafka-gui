package ui.controllers

import javafx.scene.control.ListCell
import utils.highlight


class LogItem : ListCell<HighlightMessage>() {
    override fun updateItem(item: HighlightMessage?, empty: Boolean) {
        super.updateItem(item, empty)
        if (empty || item == null) {
            text = null
            graphic = null
            return
        }

        graphic = highlight(item.message, item.searchTerm, true)
    }
}