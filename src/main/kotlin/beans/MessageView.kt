package beans

import javafx.application.Platform
import javafx.scene.control.ListView
import org.apache.camel.Body
import org.apache.camel.Header
import ui.controllers.HighlightMessage

class MessageView(private val listView: ListView<HighlightMessage>) {
    fun add(@Header("kafka.TOPIC") topic: String, @Body payload: String) {
        Platform.runLater {
            listView.items.add(HighlightMessage("$topic\n$payload"))
            listView.scrollTo(listView.items.size - 1)
        }
    }
}