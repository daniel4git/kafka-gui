package routes

import javafx.application.Platform
import javafx.collections.ObservableList
import javafx.scene.control.ListView
import org.apache.camel.builder.RouteBuilder

class GuiEndpoint(
    private val messages: ObservableList<String>,
    private val listView: ListView<String>?
) :
    RouteBuilder() {

    override fun configure() {
        from("seda:kafka")
            .process(JsonPrettyPrinter())
            .process {
                Platform.runLater {
                    messages.add(
                        """
                        |${it?.getIn()?.getHeader("kafka.TOPIC")}
                        |${it?.getIn()?.body}
                    """.trimMargin()
                    )
                    listView?.scrollTo(listView?.items.size - 1)
                }
            }
    }
}
