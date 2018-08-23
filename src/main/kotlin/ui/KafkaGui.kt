package ui

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class KafkaGui : Application() {

    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(javaClass.getResource("/KafkaGui.fxml"))
        val parent: Parent = fxmlLoader.load()
        val scene = Scene(parent, 800.0, 600.0)

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