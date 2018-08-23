package ui

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.TextArea
import javafx.scene.input.MouseEvent
import kafka.GuiEndpoint
import kafka.TopicListener
import org.apache.camel.impl.DefaultCamelContext

class LogController {
    @FXML
    var log: TextArea? = null

    @FXML
    var button: Button? = null

    fun click(event: MouseEvent) {
        val context = DefaultCamelContext()

        val listener = TopicListener(".*search.*", true)
        context.start()
        context.addRoutes(listener)
        context.addRoutes(GuiEndpoint(log))
    }
}