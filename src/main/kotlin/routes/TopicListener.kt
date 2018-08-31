package routes

import org.apache.camel.builder.RouteBuilder
import java.util.*

class TopicListener(val topic: String, val kafkahost: String) : RouteBuilder() {

    val id: String = UUID.randomUUID().toString()

    val isPattern = Regex("[^a-zA-Z0-9\\._\\-]+").containsMatchIn(topic)

    override fun configure() {
        from("kafka:$topic?brokers=$kafkahost&topicIsPattern=$isPattern")
            .routeId(id)
            .wireTap("seda:tap")
            .to("seda:kafka")
    }
}
