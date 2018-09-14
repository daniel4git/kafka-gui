package ui.views

import javafx.application.Platform
import javafx.geometry.Pos
import javafx.scene.Cursor
import javafx.scene.input.KeyCode
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import routes.TopicListener
import tornadofx.*
import ui.controllers.ConsumerController

class ConsumerPane : View("Consumers") {

    private val controller: ConsumerController by inject()

    private val topicListeners = mutableListOf<TopicListener>().observable()

    override val root = vbox {
        // This is here so that the prompt text is visible
        // when the app is first started.
        Platform.runLater { requestFocus() }

        label("Topic") {
            paddingBottom = -5.0
        }

        hbox {
            spacing = 20.0

            textfield(controller.topic) {
                promptText = ".*search.*"
                setOnKeyPressed {
                    if (it.code == KeyCode.ENTER)
                        controller.addTopic()
                }
                hgrow = Priority.ALWAYS
            }

            button("Add") {
                action {
                    controller.addTopic()
                }

                cursor = Cursor.HAND
                minWidth = 50.0
            }
        }

        listview(controller.topicList) {
            cellFormat {
                text = it.topic
            }
            multiSelect(true)
            topicListeners.bind(selectionModel.selectedItems) { it }
            vgrow = Priority.ALWAYS
        }

        hbox {
            button("Delete") {
                action { controller.deleteTopic(topicListeners) }
                cursor = Cursor.HAND
                style {
                    baseColor = c("#bf2626")
                    textFill = Color.WHITE
                }
            }
            alignment = Pos.TOP_RIGHT
        }

        paddingAll = 10.0
        spacing = 10.0
    }

}