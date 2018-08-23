# kafka-gui
Tap into Kafka


## Goals
This project is intended to help understand and develop applications which use kafka for request-response. Using the network tab in a broswer and Postman are invaluable when developing a typical RESTful web application. The overarching goal of this project is to replicate this ease of use when using request-response over a message bus instead.

This project initially started as me needing to connect kafka to postgres and elasticsearch. Thus, it leverages the [Apache Camel](https://github.com/apache/camel) project. A nice feature would be the ability to add camel routes to specific kafka topics. This could be done via Spring and drag-n-drop XML files, or a pluggable architecture.

## Compiling
As a typical maven project, running `mvn install` from the project's root directory will compile and run all the tests (of which there are 0 currently).

## Running
Once compiled, the jar file (with all the dependencies included), will be in the `target` directory. You can run it from the project's root directory via `java -jar target/kafka-gui-0.1.jar`. You can also double-click the jar file to open it.

It is important to note that this currently assumes a kafka instance is running on `localhost:9092`. Use SSH tunnels if you want to connect to a remote instance (for now).

## Using
Right now it's super basic and buggy. Running from an IDE or command line is preferred so that you can see the console output, as there is no error handling (for now).

You can consume messages from a particular topic, or use a regular expression to consume from any topics matching that expression.

## Screenshots
![Alt text](https://user-images.githubusercontent.com/9749143/44516936-3da1c400-a67b-11e8-8573-884cd909c542.png)
