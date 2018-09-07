package ui

import beans.MessageView
import javafx.geometry.Side
import javafx.scene.control.TabPane
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.impl.SimpleRegistry
import routes.Faker
import routes.GuiEndpoint
import routes.Recorder
import tornadofx.*
import ui.views.ConsumerPane
import ui.views.LogPane
import ui.views.SettingsPane
import utils.RouteActions

class Main: App(MainView::class) {
    init {
        importStylesheet("/css/styles.css")
    }
}

class MainView : View("Kafka") {

    val routeActions = RouteActions(DefaultCamelContext())

    init {
        routeActions.addRoute(Faker())
        routeActions.addRoute(Recorder())
        routeActions.addRoute(GuiEndpoint(MessageView()))
        routeActions.start()
    }

    override val root = tabpane {
        tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
        side = Side.LEFT

        tab(ConsumerPane::class)
        tab(LogPane::class)
        tab(SettingsPane::class)
    }
}
