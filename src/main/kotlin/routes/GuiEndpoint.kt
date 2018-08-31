package routes

import beans.JsonFormatter
import beans.MessageView
import javafx.scene.control.ListView
import org.apache.camel.builder.RouteBuilder

class GuiEndpoint(private val listView: ListView<String>) : RouteBuilder() {
    override fun configure() {
        from("seda:kafka")
            .bean(JsonFormatter())
            .bean(MessageView(listView))
    }
}
