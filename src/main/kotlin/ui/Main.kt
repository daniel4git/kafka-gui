package ui

import javafx.geometry.Side
import javafx.scene.control.TabPane
import tornadofx.*
import ui.views.ConsumerPane
import ui.views.LogPane
import ui.views.SettingsPane

class Main: App(MainView::class) {
    init {
        importStylesheet("/css/styles.css")
    }
}

class MainView : View("Kafka") {
    override val root = tabpane {
        tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
        side = Side.LEFT

        tab(ConsumerPane::class)
        tab(LogPane::class)
        tab(SettingsPane::class)
    }
}
