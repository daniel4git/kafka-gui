package ui.controllers

import javafx.scene.control.*
import routes.TopicListener

class TopicRow : ListCell<TopicListener>() {
    override fun updateItem(item: TopicListener?, empty: Boolean) {
        super.updateItem(item, empty)
        text = item?.topic
    }
}
