package ui.views

import tornadofx.*
import ui.controllers.SettingsController

class SettingsPane : View("Settings") {

    private val settings: SettingsController by inject()

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
            checkbox("Record messages", settings.recordMessages) {
                action {
                    settings.toggleCollectData()
                }
            }
            checkbox("Generate fake data", settings.generateFakeData) {
                action {
                    settings.toggleFakeData()
                }
            }
            checkbox("Format JSON", settings.formatJson) {
                isSelected = true
            }
            spacing = 10.0
        }

        paddingAll = 10.0
    }
}