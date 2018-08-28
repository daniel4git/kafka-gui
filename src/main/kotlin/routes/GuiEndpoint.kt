package routes

import javafx.scene.control.TextArea
import org.apache.camel.builder.RouteBuilder

class GuiEndpoint(private val textArea: TextArea?) : RouteBuilder() {

    override fun configure() {
        from("seda:kafka")
            .process(JsonPrettyPrinter())
            .process {
                it.out?.body =
                    """
                        |${it?.getIn()?.getHeader("kafka.TOPIC")}
                        |${it?.getIn()?.body}
                        |-------------------------------------
                        |
                    """.trimMargin()
            }
            .to("stream:out")
    }
}
