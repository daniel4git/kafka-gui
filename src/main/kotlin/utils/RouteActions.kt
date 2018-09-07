package utils

import kotlinx.coroutines.experimental.launch
import org.apache.camel.CamelContext
import org.apache.camel.builder.RouteBuilder
import java.util.concurrent.TimeUnit

class RouteActions(private val camelContext: CamelContext) {
    fun toggleRoute(routeId: String) {
        launch {
            if (camelContext.getRouteStatus(routeId).isStarted) {
                camelContext.stopRoute(routeId, 1, TimeUnit.SECONDS, false)
            } else {
                camelContext.startRoute(routeId)
            }
        }
    }

    fun addRoute(builder: RouteBuilder) {
        launch {
            camelContext.addRoutes(builder)
        }
    }

    fun removeRoute(routeId: String) {
        launch {
            camelContext.stopRoute(routeId, 1, TimeUnit.SECONDS, false)
            camelContext.removeRoute(routeId)
        }
    }

    fun start() {
        launch {
            camelContext.start()
        }
    }
}