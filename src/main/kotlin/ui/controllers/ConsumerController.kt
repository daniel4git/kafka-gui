package ui.controllers

import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import routes.KafkaConsumer
import tornadofx.*

class ConsumerController : Controller() {
    val topic = SimpleStringProperty()
    val consumers = mutableListOf<KafkaConsumer>().observable()

    private val settings: SettingsController by inject()
    private val routes: RouteController by inject()

    fun addConsumer() {
        if (topic.isEmpty.value) return
        if (settings.kafkaHost.isEmpty.value) return

        val consumer = KafkaConsumer(
            topic.value,
            settings.kafkaHost.value
        )
        topic.value = ""
        consumers.add(consumer)
        routes.add(consumer)
    }

    fun deleteConsumers(itemsToDelete: ObservableList<KafkaConsumer>) {
        itemsToDelete.forEach { routes.remove(it.id) }
        consumers.removeAll(itemsToDelete)
    }
}