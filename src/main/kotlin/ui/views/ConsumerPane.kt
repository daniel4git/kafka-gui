package ui.views

import javafx.geometry.Pos
import javafx.scene.input.KeyCode
import javafx.scene.layout.Priority
import routes.TopicListener
import tornadofx.*
import ui.controllers.ConsumerController

class ConsumerPane : View("Consumers") {

    val c : ConsumerController by inject()

    val selTopicListeners = mutableListOf<TopicListener>().observable()

    override val root = vbox {

        label("Topic")

        hbox {

            textfield(c.topic) {
                promptText = ".*search.*"
                setOnKeyPressed {
                    if( it.code == KeyCode.ENTER )
                        c.addTopic()
                }
            }

            hbox {
                button("Add") {
                    action {
                        c.addTopic()
                    }
                }
                alignment = Pos.TOP_RIGHT
                hgrow = Priority.ALWAYS
            }
        }

        listview(c.topicList) {
            cellFormat {
                text = it.topic
            }
            multiSelect(true)
            selTopicListeners.bind(selectionModel.selectedItems ) { it }
        }

        hbox {
            button("Delete") {
                action { c.deleteTopic(selTopicListeners) }
            }
            alignment = Pos.TOP_RIGHT
        }
    }


}