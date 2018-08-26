package routes

import javafx.scene.control.TextArea
import org.apache.camel.builder.RouteBuilder

class GuiEndpoint(private val textArea: TextArea?) : RouteBuilder() {

    private val linebreak = "\n ------------------------------------- \n"

    override fun configure() {
        from("direct:gui")
            .wireTap("direct:tap")
            .process(JsonPrettyPrinter())
            .process {
                textArea?.appendText(it?.getIn()?.body.toString() + linebreak)
            }
    }
}
