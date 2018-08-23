package ui

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.TextArea
import javafx.scene.input.MouseEvent
import kafka.GuiEndpoint
import kafka.TopicListener
import kotlinx.coroutines.experimental.async
import org.apache.camel.impl.DefaultCamelContext
import java.net.URL
import java.util.*

class LogController : Initializable {

    private val camelContext = DefaultCamelContext()

    @FXML
    var log: TextArea? = null

    @FXML
    var button: Button? = null

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        async {
            camelContext.start()
            camelContext.addRoutes(GuiEndpoint(log))
        }
    }

    fun click(event: MouseEvent) {
        async {
            val listener = TopicListener(".*search.*", true)
            camelContext.addRoutes(listener)
        }
    }
}