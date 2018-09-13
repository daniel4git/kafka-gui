package ui.models

import tornadofx.*

data class HighlightMessage(val message: String, var searchTerm: String = "")

class SearchOpenedEvent : FXEvent(EventBus.RunOn.BackgroundThread)