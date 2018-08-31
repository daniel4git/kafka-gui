package routes

import org.apache.camel.builder.RouteBuilder
import java.util.*

const val KAFKA_HOST = "localhost:9092"

class TopicListener(val topic: String) : RouteBuilder() {

    val id: String = UUID.randomUUID().toString()

    val isPattern = Regex("[^a-zA-Z0-9\\._\\-]+").containsMatchIn(topic)

    override fun configure() {
        from("kafka:$topic?brokers=$KAFKA_HOST&topicIsPattern=$isPattern")
            .routeId(id)
            .wireTap("seda:tap")
            .to("seda:kafka")
    }
}
