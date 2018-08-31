package ui

import javafx.application.Application
import javafx.event.EventHandler
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.stage.WindowEvent

class KafkaGui : Application() {

    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(javaClass.getResource("/fxml/KafkaGui.fxml"))
        val parent: Parent = fxmlLoader.load()
        val scene = Scene(parent, 800.0, 600.0)

        scene.stylesheets.add(javaClass.getResource("/styles.css").toExternalForm())

        stage.title = "Kafka Message Viewer"
        stage.scene = scene
        stage.isResizable = true
        stage.show()

        stage.onCloseRequest = EventHandler<WindowEvent> { System.exit(0) }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(KafkaGui::class.java, *args)
        }
    }
}