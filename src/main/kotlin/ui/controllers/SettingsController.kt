package ui.controllers

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import ui.MainView

class SettingsController : Controller() {

    val kafkahost = SimpleStringProperty("localhost:9092")
    val recordMessages = SimpleBooleanProperty()
    val generateFakeData = SimpleBooleanProperty()
    val formatJson = SimpleBooleanProperty()

    // see notes on fronting controllers in ConsumerController.kt
    val main_c : MainController by inject()

    fun toggleFakeData() {
        main_c.routeActions.toggleRoute("fake")
    }

    fun toggleCollectData() {
        main_c.routeActions.toggleRoute("tap")
    }

}