package ui.controllers

import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import routes.TopicListener
import tornadofx.*

class ConsumerController : Controller() {

    val topic = SimpleStringProperty()

    val topicList = mutableListOf<TopicListener>().observable()

    /**
     * This class is a facade for these other Controllers.  The common elements
     * can be further factored into an artifact like a Model component that
     * isn't so tied to a particular View.
     */
    val settings_c : SettingsController by inject()
    val main_c : MainController by inject()

    fun addTopic() {

        if( settings_c.kafkahost.value == null ) return

        if (topic.isNotEmpty.value) {
            val topicListener = TopicListener(
                topic.value,
                settings_c.kafkahost.value
            )
            topicList.add( topicListener )
            topic.value = ""
            main_c.routeActions.addRoute(topicListener)
        }
    }

    fun deleteTopic(itemsToDelete : ObservableList<TopicListener>) {
        itemsToDelete.forEach { main_c.routeActions.removeRoute(it.id) }
        topicList.removeAll( itemsToDelete )
    }
}