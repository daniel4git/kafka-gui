package ui.views

import javafx.scene.layout.AnchorPane
import tornadofx.*

class SettingsPane : View("Settings") {
    override val root: AnchorPane by fxml("/views/SettingsPane.fxml")

    fun toggleFakeData() {
    }

    fun toggleCollectData() {
    }
}