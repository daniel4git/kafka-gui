package ui.controllers

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.*
import kotlinx.coroutines.experimental.launch
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.impl.DefaultCamelContext
import routes.Faker
import routes.GuiEndpoint
import routes.Recorder
import routes.TopicListener
import java.net.URL
import java.util.*

class LogController : Initializable {

    private val camelContext = DefaultCamelContext()
    var topics: ObservableList<TopicListener> = FXCollections.observableArrayList<TopicListener>()

    @FXML var messages: ListView<String>? = null
    @FXML var topic: TextField? = null
    @FXML var isPattern: CheckBox? = null
    @FXML var topicList: ListView<TopicListener>? = null
    @FXML var deleteButton: Button? = null

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        camelContext.start()
        camelContext.addRoutes(Faker())
        camelContext.addRoutes(Recorder())
        camelContext.addRoutes(GuiEndpoint(messages))

        topicList?.setCellFactory { TopicRow() }
    }

    fun addTopic() {
        val topic = topic?.text ?: ""
        val isPattern = isPattern?.isSelected ?: false

        if (topic.isNotEmpty()) {
            val topicListener = TopicListener(topic, isPattern)
            addRoute(topicListener, camelContext)
            topics.add(topicListener)
        }
    }

    fun toggleFakeData() {
        toggleRoute("fake", camelContext)
    }

    fun toggleCollectData() {
        toggleRoute("tap", camelContext)
    }

    fun delete() {
        topicList?.selectionModel?.selectedItems?.forEach {
            removeRoute(it.id, camelContext)
            topics.remove(it)
        }
    }
}

