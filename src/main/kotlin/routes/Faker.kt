package routes

import org.apache.camel.builder.RouteBuilder
import java.util.*

class Faker : RouteBuilder() {
    override fun configure() {
        from("timer:foo?period=500")
            .routeId("fake")
            .autoStartup(false)
            .process {
                it?.out?.setHeader("kafka.TOPIC", "fake")
                it?.out?.body = "{\"message\": \"${UUID.randomUUID()}\"}"
            }
            .wireTap("seda:tap")
            .to("seda:kafka")
    }
}