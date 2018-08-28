package routes

import org.apache.camel.builder.RouteBuilder

class Faker : RouteBuilder() {
    override fun configure() {
        from("timer:foo?period=500")
            .routeId("fake")
            .autoStartup(false)
            .process {
                it?.out?.setHeader("kafka.TOPIC", "fake")
                it?.out?.body = "Hey"
            }
            .to("seda:kafka")
    }
}