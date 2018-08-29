package beans

import javafx.application.Platform
import javafx.collections.ObservableList
import javafx.scene.control.ListView
import org.apache.camel.Body
import org.apache.camel.Header

class MessageView(
    private val messages: ObservableList<String>,
    private val listView: ListView<String>?
) {
    fun add(@Header("kafka.TOPIC") topic: String, @Body payload: String) {
        Platform.runLater {
            messages.add("$topic\n$payload")
            listView?.scrollTo(listView.items.size - 1)
        }
    }
}