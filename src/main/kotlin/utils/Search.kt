package utils

import javafx.scene.paint.Color
import javafx.scene.text.Text
import javafx.scene.text.TextFlow

fun highlight(text: String, searchTerm: String, ignoreCase: Boolean): TextFlow {
    if (searchTerm.isEmpty()) {
        return TextFlow(Text(text))
    }

    // This regex lets us keep the search term when we apply the split function
    val regex = "((?<=$searchTerm)|(?=$searchTerm))"
        .toRegex(RegexOption.IGNORE_CASE)

    val richText = text
        .split(regex)
        .map { if (it.equals(searchTerm, ignoreCase)) createHighlight(it) else Text(it) }

    return TextFlow(*richText.toTypedArray())
}

private fun createHighlight(s: String): Text {
    val text = Text(s)
    text.fill = Color.ORANGE
    return text
}
