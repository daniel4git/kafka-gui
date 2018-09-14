package ui.views

import tornadofx.*
import ui.controllers.FormatChangedEvent
import ui.controllers.RouteController
import ui.controllers.SettingsController

class SettingsPane : View("Settings") {

    private val settings: SettingsController by inject()
    private val routes: RouteController by inject()

    override val root = vbox {
        vbox {
            label("Kafka Host") {
                paddingBottom = -5.0
            }
            hbox {
                textfield(settings.kafkaHost) {
                    prefWidth = 300.0
                }
                paddingBottom = 10.0
            }
            checkbox("Record messages") {
                action {
                    routes.toggle("tap", isSelected)
                }
            }
            checkbox("Generate fake data") {
                action {
                    routes.toggle("fake", isSelected)
                }
            }
            checkbox("Format JSON", settings.formatJson) {
                action {
                    fire(FormatChangedEvent())
                }
            }
            spacing = 10.0
        }
        paddingAll = 10.0
    }
}