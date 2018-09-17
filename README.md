# kafka-gui
Tap into Kafka


## Getting Started
You can grab the latest release [here](https://github.com/spearskw/kafka-gui/releases/download/0.3/kafka-gui-0.3.dmg),
or [build it yourself](#building).

The easiest way to get started is to type `.*` into the `Topic` field and click
`Add`. This will start consuming messages on all topics. You can also make more
specific patterns or topic names so that you don't get a flood of data.
![Consumers](https://user-images.githubusercontent.com/9749143/45649698-f394de00-ba80-11e8-80ff-70983974735a.png)

The messages will start popping up on the `Log` tab. You can filter the messages
by using the `cmd f` key combination to open a filter textfield. You can close
the filter by hitting `esc`.
![Log](https://media.giphy.com/media/93ontbe3mvrI0Asqi1/giphy.gif)

You can change the host / port in the `Settings` tab. You can also toggle JSON
formatting, fake data generation (useful for testing), and writing the messages
to a log file. The message log is saved in the .app folder when running as a
MacOS app. When running as a jar, messages are saved in the same location you
launched the jar from.
![Settings](https://user-images.githubusercontent.com/9749143/45649717-ff80a000-ba80-11e8-8a6b-fb6390aec3f7.png)

#### Gotchas
This software is very much early stages. It has no error handling and uses an
exorbitant amount of memory until eventually crashing. "Eventually" here could
mean a minute or so.

## Building
As a typical maven project the only thing you need to do is `mvn package` from the
root directory. **This will fail** because it will try to make a MacOS specific
DMG and I don't have the icon set in source control. It will successfully create
a jar-with-dependencies before failing though, so you can still use that jar.

## Hacking
This project is using [Apache Camel](https://github.com/apache/camel/blob/master/README.md)
for message routing, [TornadoFX](https://github.com/edvin/tornadofx)
for the GUI, and [Kotlin](https://kotlinlang.org/docs/reference/) for everything else.

The `routes` folder defines how messages will flow through the system. I want
to keep the routes as isolated, pure, and simple as possible.

