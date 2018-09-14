package ui.controllers

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class SettingsController : Controller() {
    val kafkaHost = SimpleStringProperty("localhost:9092")
    val formatJson = SimpleBooleanProperty(true)
}