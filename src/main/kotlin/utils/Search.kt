package utils

import javafx.scene.paint.Color
import javafx.scene.text.Text
import javafx.scene.text.TextFlow

fun highlight(
    text: String,
    searchTerm: String,
    ignoreCase: Boolean
): TextFlow {
    
    if (searchTerm.isEmpty()) {
        return TextFlow(Text(text))
    }

    // Normally we lose the delimiter we split on, but we want to keep it
    val keepSearchTerm = "((?<=$searchTerm)|(?=$searchTerm))"
        .toRegex(RegexOption.IGNORE_CASE)

    val richText = text
        .split(keepSearchTerm)
        .map {
            if (it.equals(searchTerm, ignoreCase))
                createHighlight(it)
            else
                Text(it)
        }

    return TextFlow(*richText.toTypedArray())
}

private fun createHighlight(s: String): Text {
    val text = Text(s)
    text.fill = Color.ORANGE
    return text
}
