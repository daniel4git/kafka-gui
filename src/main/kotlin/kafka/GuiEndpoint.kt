package kafka

import javafx.scene.control.TextArea
import org.apache.camel.Exchange
import org.apache.camel.Processor
import org.apache.camel.builder.RouteBuilder

class GuiEndpoint(log: TextArea?) : RouteBuilder() {
    private var processor: Processor? = null

    init {
        this.processor = GuiProcessor(log)
    }

    override fun configure() {
        from("direct:gui")
            .process(processor)
    }
}

private class GuiProcessor(val logTextArea: TextArea?) : Processor {

    private val linebreak = "\n ------------------------------------- \n"

    override fun process(exchange: Exchange?) {
        logTextArea?.appendText(exchange?.getIn()?.body.toString() + linebreak)
    }
}