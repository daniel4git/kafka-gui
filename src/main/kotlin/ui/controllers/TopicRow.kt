package ui.controllers

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.*
import javafx.util.Callback
import routes.TopicListener
import java.io.IOException


class TopicRow : ListCell<TopicListener>() {

    @FXML var topic: Label? = null
    @FXML var toggle: Button? = null

    private var isEnabled = true

    init {
        loadFXML()
    }

    override fun updateItem(item: TopicListener?, empty: Boolean) {
        super.updateItem(item, empty)
        if (empty || item == null) {
            text = null
            contentDisplay = ContentDisplay.TEXT_ONLY
        } else {
            topic?.text = item.topic
            toggle?.text = if (isEnabled) "stop" else "Start"
            contentDisplay = ContentDisplay.GRAPHIC_ONLY
            toggle?.setOnMouseClicked{
                toggleRoute(item.id, item.context)
                isEnabled = !isEnabled
            }
        }
    }

    private fun loadFXML() {
        try {
            val loader = FXMLLoader(javaClass.getResource("/fxml/TopicRow.fxml"))
            loader.setController(this)
            loader.setRoot(this)
            loader.load<Any>()
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }
}

class TopicRowFactory : Callback<ListView<TopicListener>, ListCell<TopicListener>> {
    override fun call(param: ListView<TopicListener>): ListCell<TopicListener> {
        return TopicRow()
    }
}