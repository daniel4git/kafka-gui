package ui.views

import javafx.application.Platform
import javafx.scene.control.ListCell
import javafx.scene.control.ListView
import javafx.scene.control.TextField
import javafx.scene.layout.GridPane
import routes.TopicListener
import tornadofx.*
import ui.MainView

class ConsumerPane : View("Consumers") {
    override val root: GridPane by fxml("/views/ConsumerPane.fxml")

    private val topic: TextField by fxid()
    private val topicList: ListView<TopicListener> by fxid()

    init {
        topicList.setCellFactory { TopicRow() }
        Platform.runLater { topic.parent.requestFocus() }
    }

    fun addTopic() {
        if (topic.text.isNotEmpty()) {
            val topicListener = TopicListener(
                topic.text,
                find(SettingsPane::class).kafkahost.text
            )
            find(MainView::class).routeActions.addRoute(topicListener)
            topicList.items.add(topicListener)
        }
    }

    fun deleteTopic() {
        topicList.selectionModel.selectedItems.forEach {
            find(MainView::class).routeActions.removeRoute(it.id)
            topicList.items.remove(it)
        }
    }
}

class TopicRow : ListCell<TopicListener>() {
    override fun updateItem(item: TopicListener?, empty: Boolean) {
        super.updateItem(item, empty)
        text = item?.topic
    }
}