package ui.controllers

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.ListView
import javafx.scene.control.TextField
import org.apache.camel.impl.DefaultCamelContext
import routes.Faker
import routes.GuiEndpoint
import routes.Recorder
import routes.TopicListener
import java.net.URL
import java.util.*
import javafx.application.Platform
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.input.KeyEvent
import utils.RouteActions


class ConsumerController : Initializable {

    var topics: ObservableList<TopicListener> = FXCollections.observableArrayList<TopicListener>()
    lateinit var routeActions: RouteActions

    @FXML lateinit var messages: ListView<HighlightMessage>
    @FXML lateinit var topic: TextField
    @FXML lateinit var topicList: ListView<TopicListener>
    @FXML lateinit var kafkahost: TextField
    @FXML lateinit var searchField: TextField

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        topicList.setCellFactory { TopicRow() }
        messages.setCellFactory { LogItem() }

        routeActions = RouteActions(DefaultCamelContext())
        routeActions.addRoute(Faker())
        routeActions.addRoute(Recorder())
        routeActions.addRoute(GuiEndpoint(messages))
        routeActions.start()

        // This is a hack to get the prompt text to show up on the
        // topic text field when first booted
        Platform.runLater { topic.parent.requestFocus() }
    }

    fun addTopic() {
        if (topic.text.isNotEmpty()) {
            val topicListener = TopicListener(topic.text, kafkahost.text)
            routeActions.addRoute(topicListener)
            topics.add(topicListener)
        }
    }

    fun deleteTopic() {
        topicList.selectionModel.selectedItems.forEach {
            routeActions.removeRoute(it.id)
            topics.remove(it)
        }
    }

    fun toggleFakeData() {
        routeActions.toggleRoute("fake")
    }

    fun toggleCollectData() {
        routeActions.toggleRoute("tap")
    }

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
        messages.items.forEach {
            it.searchTerm = searchField.text
        }
        messages.refresh()
    }
}

data class HighlightMessage(val message: String, var searchTerm: String = "")