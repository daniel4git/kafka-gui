package ui

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.*
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.impl.DefaultCamelContext
import routing.GuiEndpoint
import routing.TopicListener
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

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        table?.items?.setAll(topics)
        camelContext.start()
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

    fun toggleFakeData( ) {
        if (fake?.isSelected == false) {
            camelContext.stopRoute("fake")
            return
        }

        camelContext.addRoutes(object : RouteBuilder() {
            override fun configure() {
                from("timer:foo")
                    .routeId("fake")
                    .process {
                        it?.out?.body = "Hey"
                    }
                    .to("direct:gui")
            }
        })
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