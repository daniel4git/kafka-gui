package routes

import beans.JsonFormatter
import beans.MessageView
import javafx.scene.control.ListView
import org.apache.camel.builder.RouteBuilder
import ui.views.HighlightMessage

class GuiEndpoint(
    private val listView: ListView<HighlightMessage>
) : RouteBuilder() {

    override fun configure() {
        from("seda:kafka")
            .bean(JsonFormatter())
            .bean(MessageView(listView))
    }
}
