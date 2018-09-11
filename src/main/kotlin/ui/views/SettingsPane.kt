package ui.views

import tornadofx.*
import ui.controllers.SettingsController

class SettingsPane : View("Settings") {

    val c : SettingsController by inject()

    override val root = vbox {
        vbox {
            label("Kafka Host")
            textfield(c.kafkahost)
            checkbox("Record messages", c.recordMessages) {
                action {
                    c.toggleCollectData()
                }
            }
            checkbox("Generate fake data", c.generateFakeData) {
                action {
                    c.toggleFakeData()
                }
            }
            checkbox("Format JSON", c.formatJson)
        }
    }
}