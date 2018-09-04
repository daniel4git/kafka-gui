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
        val root: Parent = fxmlLoader.load()

        val scene = Scene(root, 800.0, 600.0)
        scene.stylesheets.add(javaClass.getResource("/styles.css").toExternalForm())

        stage.onCloseRequest = EventHandler<WindowEvent> { System.exit(0) }
        stage.title = "Kafka Message Viewer"
        stage.scene = scene
        stage.isResizable = true
        stage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(KafkaGui::class.java, *args)
        }
    }
}