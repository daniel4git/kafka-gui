package routes

import org.apache.camel.builder.RouteBuilder

class GuiEndpoint : RouteBuilder() {

    override fun configure() {
        from("seda:kafka")
            .bean("messageView",
                "add(\${header.kafka.TOPIC},\${body})")
    }
}
