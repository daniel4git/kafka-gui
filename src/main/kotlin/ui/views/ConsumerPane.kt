package ui.views

import javafx.application.Platform
import javafx.geometry.Pos
import javafx.scene.Cursor
import javafx.scene.input.KeyCode
import javafx.scene.layout.Priority
import routes.TopicListener
import tornadofx.*
import ui.controllers.ConsumerController

class ConsumerPane : View("Consumers") {

    private val c: ConsumerController by inject()

    private val selTopicListeners = mutableListOf<TopicListener>().observable()

    override val root = vbox {
        // This is here so that the prompt text is visible
        // when the app is first started.
        Platform.runLater { requestFocus() }

        label("Topic") {
            paddingBottom = -5.0
        }

        hbox {
            spacing = 20.0

            textfield(c.topic) {
                promptText = ".*search.*"
                setOnKeyPressed {
                    if (it.code == KeyCode.ENTER)
                        c.addTopic()
                }
                hgrow = Priority.ALWAYS
            }

            button("Add") {
                action {
                    c.addTopic()
                }

                cursor = Cursor.HAND
                minWidth = 50.0
            }
        }

        listview(c.topicList) {
            cellFormat {
                text = it.topic
            }
            multiSelect(true)
            selTopicListeners.bind(selectionModel.selectedItems) { it }
            vgrow = Priority.ALWAYS
        }

        hbox {
            button("Delete") {
                action { c.deleteTopic(selTopicListeners) }
                cursor = Cursor.HAND
                addClass(Styles.deleteButton)
            }
            alignment = Pos.TOP_RIGHT
        }

        paddingAll = 10.0
        spacing = 10.0
    }

}