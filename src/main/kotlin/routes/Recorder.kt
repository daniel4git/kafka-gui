package routes

import org.apache.camel.builder.RouteBuilder
import java.time.LocalDateTime

class Recorder : RouteBuilder() {
    override fun configure() {
        from("seda:tap")
            .routeId("tap")
            .autoStartup(false)
            .process {
                val map = mutableMapOf<String, String>()
                map["time"] = LocalDateTime.now().toString()
                map["topic"] = it?.getIn()?.getHeader("kafka.TOPIC").toString()
                map["message"] = it?.getIn()?.body.toString()
                it?.getIn()?.body = map
            }
            .marshal().csv()
            .to("file:messages?fileExist=append&fileName=log.csv")
            .end()
    }
}