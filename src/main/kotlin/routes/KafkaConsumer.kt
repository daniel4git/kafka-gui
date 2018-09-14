package routes

import org.apache.camel.builder.RouteBuilder
import java.util.*

class KafkaConsumer(
    val topic: String,
    private val kafkaHost: String
) : RouteBuilder() {

    val id: String = UUID.randomUUID().toString()

    val isPattern = Regex("[^a-zA-Z0-9._\\-]+").containsMatchIn(topic)

    override fun configure() {
        from("kafka:$topic?brokers=$kafkaHost&topicIsPattern=$isPattern")
            .routeId(id)
            .wireTap("seda:tap")
            .to("seda:kafka")
    }
}
