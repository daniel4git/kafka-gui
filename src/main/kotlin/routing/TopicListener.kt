package routing

import org.apache.camel.builder.RouteBuilder
import java.util.*

const val KAFKA_HOST = "localhost:9092"

class TopicListener(
    val topic: String,
    val isPattern: Boolean = false
) : RouteBuilder() {

    val id: String = UUID.randomUUID().toString()

    override fun configure() {
        from("kafka:$topic?brokers=$KAFKA_HOST&topicIsPattern=$isPattern")
            .routeId(id)
            .process(JsonPrettyPrinter())
            .to("direct:gui")
    }
}