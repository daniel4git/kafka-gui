package routes

import org.apache.camel.builder.RouteBuilder

class Recorder : RouteBuilder() {
    override fun configure() {
        from("direct:tap")
            .routeId("tap")
            .autoStartup(false)
            .process { it?.out?.body = it?.getIn()?.body.toString() + "\n" }
            .to("file:messages?fileExist=append&fileName=log.txt")
    }
}