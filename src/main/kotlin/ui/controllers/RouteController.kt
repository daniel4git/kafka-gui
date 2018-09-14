package ui.controllers

import org.apache.camel.builder.RouteBuilder
import org.apache.camel.impl.DefaultCamelContext
import tornadofx.*
import java.util.concurrent.TimeUnit

class RouteController : Controller() {

    private val camelContext = DefaultCamelContext()

    fun toggleRoute(routeId: String) {
        runAsync {
            if (camelContext.getRouteStatus(routeId).isStarted) {
                camelContext.stopRoute(routeId, 1, TimeUnit.SECONDS, false)
            } else {
                camelContext.startRoute(routeId)
            }
        }
    }

    fun addRoute(builder: RouteBuilder) {
        runAsync {
            camelContext.addRoutes(builder)
        }
    }

    fun removeRoute(routeId: String) {
        runAsync {
            camelContext.stopRoute(routeId, 1, TimeUnit.SECONDS, false)
            camelContext.removeRoute(routeId)
        }
    }

    fun start() {
        runAsync {
            camelContext.start()
        }
    }
}