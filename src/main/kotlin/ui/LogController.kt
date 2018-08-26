package ui

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.*
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

    @FXML var log: TextArea? = null
    @FXML var add: Button? = null
    @FXML var topic: TextField? = null
    @FXML var isPattern: CheckBox? = null
    @FXML var table: TableView<TopicRow>? = null
    @FXML var fake: CheckBox? = null
    @FXML var collect: CheckBox? = null

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        table?.items?.setAll(topics)
        camelContext.start()
        camelContext.addRoutes(Faker())
        camelContext.addRoutes(Recorder())
        camelContext.addRoutes(GuiEndpoint(log))
    }

    fun addTopic() {
        val topic = topic?.text ?: ""
        val isPattern = isPattern?.isSelected ?: false

        if (topic.isNotEmpty()) {
            topics.add(TopicRow(topic, isPattern, true))
            camelContext.addRoutes(TopicListener(topic, isPattern))
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
}

class TopicRow constructor(topic: String, isPattern: Boolean, enabled: Boolean) {
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