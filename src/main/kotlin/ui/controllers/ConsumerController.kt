package ui.controllers

import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import routes.TopicListener
import tornadofx.*

class ConsumerController : Controller() {

    val topic = SimpleStringProperty()

    val topicList = mutableListOf<TopicListener>().observable()

    private val settings : SettingsController by inject()
    private val routes : RouteController by inject()

    fun addTopic() {

        if( settings.kafkahost.value == null ) return

        if (topic.isNotEmpty.value) {
            val topicListener = TopicListener(
                topic.value,
                settings.kafkahost.value
            )
            topicList.add( topicListener )
            topic.value = ""
            routes.addRoute(topicListener)
        }
    }

    fun deleteTopic(itemsToDelete : ObservableList<TopicListener>) {
        itemsToDelete.forEach { routes.removeRoute(it.id) }
        topicList.removeAll( itemsToDelete )
    }
}