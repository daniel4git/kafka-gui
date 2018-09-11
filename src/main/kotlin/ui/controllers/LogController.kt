package ui.controllers

import tornadofx.*
import ui.views.HighlightMessage

class LogController : Controller() {

    val messageList = mutableListOf<HighlightMessage>().observable()
}