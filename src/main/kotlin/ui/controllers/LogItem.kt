package ui.controllers

import javafx.scene.control.ListCell
import utils.buildHighlight


class LogItem : ListCell<HighlightMessage>() {
    override fun updateItem(item: HighlightMessage?, empty: Boolean) {
        super.updateItem(item, empty)
        if (empty || item == null) {
            text = null
            graphic = null
            return
        }

        graphic = buildHighlight(item.message, item.searchTerm)
    }
}