package routes

import ui.models.MessageModel
import org.apache.camel.builder.RouteBuilder

class GuiEndpoint(private val messageModel: MessageModel) : RouteBuilder() {
    override fun configure() {
        from("seda:kafka")
            .bean(messageModel)
    }
}
