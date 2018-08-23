package ui

import javafx.beans.property.SimpleBooleanProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.*
import javafx.scene.input.MouseEvent
import kafka.GuiEndpoint
import kafka.TopicListener
import kotlinx.coroutines.experimental.async
import org.apache.camel.impl.DefaultCamelContext
import java.net.URL
import java.util.*
import javafx.beans.property.SimpleStringProperty

class LogController : Initializable {

    private val camelContext = DefaultCamelContext()
    var topics: ObservableList<TopicRow> = FXCollections.observableArrayList<TopicRow>()

    @FXML var log: TextArea? = null
    @FXML var add: Button? = null
    @FXML var topic: TextField? = null
    @FXML var isPattern: CheckBox? = null
    @FXML var table: TableView<TopicRow>? = null

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        table?.items?.setAll(topics)
        async {
            camelContext.start()
            camelContext.addRoutes(GuiEndpoint(log))
        }
    }

    fun addTopic(event: MouseEvent) {
        val topic = topic?.text ?: ""
        val isPattern = isPattern?.isSelected ?: false

        if (topic.isNotEmpty()) {
            topics.add(TopicRow(topic, isPattern, true))
            camelContext.addRoutes(TopicListener(topic, isPattern))
        }
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