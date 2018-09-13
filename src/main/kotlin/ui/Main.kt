package ui

import javafx.geometry.Side
import javafx.scene.control.TabPane
import routes.Faker
import routes.GuiEndpoint
import routes.Recorder
import tornadofx.*
import ui.controllers.MainController
import ui.models.MessageModel
import ui.views.ConsumerPane
import ui.views.LogPane
import ui.views.SettingsPane

class Main : App(MainView::class)

class MainView : View("Kafka") {

    private val c: MainController by inject()

    override fun onDock() {

        val endpointArg = find<MessageModel>()  // fresh instance

        // comes up w/o a glitch; keep fx stuff out of here
        runAsync {
            c.routeActions.addRoute(Faker())
            c.routeActions.addRoute(Recorder())
            c.routeActions.addRoute(GuiEndpoint(endpointArg))
            c.routeActions.start()
        }
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