package routes

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import org.apache.camel.Exchange
import org.apache.camel.Processor

class JsonPrettyPrinter : Processor {
    override fun process(exchange: Exchange?) {
        val gson = GsonBuilder().setPrettyPrinting().create()
        val je = JsonParser().parse(exchange?.getIn()?.body.toString())
        exchange?.out?.body = gson.toJson(je)
    }
}