package ui

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.*
import kotlinx.coroutines.experimental.launch
import org.apache.camel.impl.DefaultCamelContext
import routes.Faker
import routes.GuiEndpoint
import routes.Recorder
import routes.TopicListener
import java.net.URL
import java.util.*

class LogController : Initializable {

    private val camelContext = DefaultCamelContext()
    var topics: ObservableList<TopicRow> = FXCollections.observableArrayList<TopicRow>()

    @FXML var messages: ListView<String>? = null
    @FXML var add: Button? = null
    @FXML var topic: TextField? = null
    @FXML var isPattern: CheckBox? = null
    @FXML var table: TableView<TopicRow>? = null
    @FXML var fake: CheckBox? = null
    @FXML var collect: CheckBox? = null
    @FXML var remove: Button? = null

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        camelContext.start()
        camelContext.addRoutes(Faker())
        camelContext.addRoutes(Recorder())
        camelContext.addRoutes(GuiEndpoint(messages))
    }

    fun addTopic() {
        val topic = topic?.text ?: ""
        val isPattern = isPattern?.isSelected ?: false

        if (topic.isNotEmpty()) {
            val topicListener = TopicListener(topic, isPattern)
            camelContext.addRoutes(topicListener)
            topics.add(TopicRow(topic, isPattern, true, topicListener.id))
        }
    }

    fun toggleRoute(shouldStart: Boolean, routeId: String) {
        if (shouldStart) {
            camelContext.startRoute(routeId)
        } else {
            camelContext.stopRoute(routeId)
        }
    }

    fun toggleFakeData() {
        toggleRoute(fake?.isSelected == true, "fake")
    }

    fun toggleCollectData() {
        toggleRoute(collect?.isSelected == true, "tap")
    }

    fun removeLastRoute() {
        launch {
            camelContext.stopRoute(topics.last().id)
            camelContext.removeRoute(topics.last().id)
            topics.remove(topics.last())
        }
    }
}

class TopicRow constructor(topic: String, isPattern: Boolean, enabled: Boolean, val id: String) {
    private val topic = SimpleStringProperty(topic)
    private val isPattern = SimpleBooleanProperty(isPattern)
    private val enabled = SimpleBooleanProperty(enabled)

    fun getTopic(): String {
        return topic.value
    }

    fun getIsPattern(): Boolean {
        return isPattern.value
    }

    fun getEnabled(): Boolean {
        return enabled.value
    }
}