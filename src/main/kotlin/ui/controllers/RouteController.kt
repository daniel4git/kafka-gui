package ui.controllers

import org.apache.camel.builder.RouteBuilder
import org.apache.camel.impl.DefaultCamelContext
import tornadofx.*
import java.util.concurrent.TimeUnit

class RouteController : Controller() {

    private val camelContext = DefaultCamelContext()

    init {
        runAsync {
            camelContext.start()
        }
    }

    fun add(builder: RouteBuilder) {
        runAsync {
            camelContext.addRoutes(builder)
        }
    }

    fun remove(routeId: String) {
        runAsync {
            camelContext.stopRoute(routeId, 1, TimeUnit.SECONDS, false)
            camelContext.removeRoute(routeId)
        }
    }

    fun start(routeId: String) {
        runAsync {
            camelContext.startRoute(routeId)
        }
    }

    fun stop(routeId: String) {
        runAsync {
            camelContext.stopRoute(routeId, 1, TimeUnit.SECONDS, false)
        }
    }

    fun toggle(routeId: String, isEnabled: Boolean) {
        if (isEnabled) {
            start(routeId)
        } else {
            stop(routeId)
        }
    }
}