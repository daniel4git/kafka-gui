package ui.controllers

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class SettingsController : Controller() {

    val kafkahost = SimpleStringProperty("localhost:9092")
    val recordMessages = SimpleBooleanProperty()
    val generateFakeData = SimpleBooleanProperty()
    val formatJson = SimpleBooleanProperty()

    private val routes : RouteController by inject()

    fun toggleFakeData() {
        routes.toggleRoute("fake")
    }

    fun toggleCollectData() {
        routes.toggleRoute("tap")
    }

}