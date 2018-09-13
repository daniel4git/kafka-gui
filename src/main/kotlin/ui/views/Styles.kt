package ui.views

import javafx.scene.paint.Color
import tornadofx.*

class Styles : Stylesheet() {

    companion object {
        val deleteButton by cssclass()
    }

    init {
        deleteButton {
            baseColor = c("#bf2626")
            textFill = Color.WHITE
        }
    }
}