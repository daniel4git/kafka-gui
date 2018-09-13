package ui.views

import tornadofx.*
import ui.controllers.SettingsController

class SettingsPane : View("Settings") {

    private val c: SettingsController by inject()

    override val root = vbox {
        vbox {
            label("Kafka Host") {
                paddingBottom = -5.0
            }
            hbox {
                textfield(c.kafkahost) {
                    prefWidth = 300.0
                }
                paddingBottom = 10.0
            }
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
            checkbox("Format JSON", c.formatJson) {
                isSelected = true
            }
            spacing = 10.0
        }

        paddingAll = 10.0
    }
}