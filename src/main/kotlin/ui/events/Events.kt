package ui.events

import tornadofx.*
import ui.models.KafkaMessage

class SearchOpenedEvent : FXEvent(EventBus.RunOn.BackgroundThread)
class FormatChangedEvent : FXEvent(EventBus.RunOn.BackgroundThread)
class MessageAddedEvent(val message: KafkaMessage) : FXEvent(EventBus.RunOn.BackgroundThread)
