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


class LogController : Initializable {

    var topics: ObservableList<TopicListener> = FXCollections.observableArrayList<TopicListener>()
    lateinit var routeActions: RouteActions

    @FXML lateinit var messages: ListView<String>
    @FXML lateinit var topic: TextField
    @FXML lateinit var topicList: ListView<TopicListener>
    @FXML lateinit var kafkahost: TextField

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        topicList.setCellFactory { TopicRow() }
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
}

