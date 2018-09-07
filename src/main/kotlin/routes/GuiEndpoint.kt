package routes

import beans.MessageView
import org.apache.camel.builder.RouteBuilder

class GuiEndpoint(private val messageView: MessageView) : RouteBuilder() {
    override fun configure() {
        from("seda:kafka")
            .bean(messageView)
    }
}
