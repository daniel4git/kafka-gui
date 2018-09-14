package ui

import javafx.geometry.Side
import javafx.scene.control.TabPane
import routes.Faker
import routes.GuiEndpoint
import routes.Recorder
import tornadofx.*
import ui.controllers.RouteController
import ui.views.ConsumerPane
import ui.views.LogPane
import ui.views.SettingsPane

class Main : App(MainView::class)

class MainView : View("Kafka") {

    private val routes: RouteController by inject()

    override fun onDock() {
        routes.addRoute(Faker())
        routes.addRoute(Recorder())
        routes.addRoute(GuiEndpoint(find())) // TODO this is too magical
        routes.start()
    }

    override val root = tabpane {
        tab(ConsumerPane::class)
        tab(LogPane::class)
        tab(SettingsPane::class)

        tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
        side = Side.LEFT

        prefWidth = 1024.0
        prefHeight = 768.0
    }
}

fun main(args: Array<String>) {
    launch<Main>(args)
}