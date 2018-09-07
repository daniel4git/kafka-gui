package ui.views

import javafx.scene.control.TextField
import javafx.scene.layout.AnchorPane
import tornadofx.*
import ui.MainView

class SettingsPane : View("Settings") {
    override val root: AnchorPane by fxml("/views/SettingsPane.fxml")

    val kafkahost: TextField by fxid()

    fun toggleFakeData() {
        find(MainView::class).routeActions.toggleRoute("fake")
    }

    fun toggleCollectData() {
        find(MainView::class).routeActions.toggleRoute("tap")
    }
}