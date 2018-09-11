package ui.controllers

import org.apache.camel.impl.DefaultCamelContext
import tornadofx.*
import utils.RouteActions

class MainController : Controller() {

    val routeActions = RouteActions(DefaultCamelContext())
}