package utils

import javafx.scene.paint.Color
import javafx.scene.text.Text
import javafx.scene.text.TextFlow

fun buildHighlight(text: String, searchTerm: String): TextFlow {
    if (searchTerm.isEmpty()) {
        return TextFlow(Text(text))
    }

    val parts = text.split(searchTerm).flatMap { it ->
        listOf(Text(it), highlight(searchTerm))
    }

    val reducedParts = parts
        .slice(0 until parts.lastIndex)
        .filter{it.text.isNotEmpty()}

    return TextFlow(*reducedParts.toTypedArray())
}

fun highlight(s: String): Text {
    val text = Text(s)
    text.fill = Color.ORANGE
    return text
}
