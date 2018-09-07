# kafka-gui
Tap into Kafka


## Getting Started
You can grab the latest release [here](https://github.com/spearskw/kafka-gui/releases/download/0.1/kafka-gui-0.1.jar),
or [build it yourself](#building).

Console output can be important, so it is best to run it from the command line
via `java -jar <path-to-jar>`. 

Once you have it running, you will be greeted
with this screen:

![first boot screen](https://user-images.githubusercontent.com/9749143/44939713-4c077400-ad3c-11e8-97ca-3b0832878d8b.png)

_Assuming Kafka is running at `localhost:9092`. If not, change the host in the Settings tab_

To start consuming kafka messages, enter a topic name or pattern into the `Topic` field
and click the `Add` button. As you add topics or topic patterns, they will show up
in the list on the `Listeners` tab. 

To see the messages that are being consumed, click on the `Log` tab.

To stop consuming from a topic or pattern, select it in the topic list and click
the `Delete` button

![screen with topics](https://user-images.githubusercontent.com/9749143/44939738-6d686000-ad3c-11e8-943e-9913805f9cca.png)

#### Gotchas
This software is very much early stages, and has no error handling.
It can take several seconds to successfully subscribe to or remove topics,
and tens of seconds for things to fail (because there's a certain number of retries).
There are no indications in the UI when things fail, therefore
watch the console logs if things seem amiss.

## Building
As a typical maven project the only thing you need to do is `mvn package` from the
root directory. This will produce two `.jar` files (one with all the dependencies included and one without), as well as a `.dmg` and `.app` for running on a Mac. You can find all of these in the `target` directory.

## Hacking
This project is using [Apache Camel](https://github.com/apache/camel/blob/master/README.md)
for message routing, [TornadoFX](https://github.com/edvin/tornadofx)
for the GUI, and [Kotlin](https://kotlinlang.org/docs/reference/) for everything else.

The `routes` folder defines how messages will flow through the system. I want
to keep the routes as isolated and pure as possible.

The layout and structure of the UI is kept to fxml and css as much as possible.
I drag-n-drop the UI using [gluon](https://gluonhq.com/products/scene-builder/) like a cretin.

The entry point of the application is [here](https://github.com/spearskw/kafka-gui/blob/master/src/main/kotlin/ui/Main.kt)

Why such a weird stack? It started with me wanting to play around with routing messages between Kafka, Postgres, Elasticsearch, and a REST controller. Camel provides a nice Domain Specific Language that let's me focus on how messages flow through the system. Since Camel is written in Java, I need a JVM stack.
