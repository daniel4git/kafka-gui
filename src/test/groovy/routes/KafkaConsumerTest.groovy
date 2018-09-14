package routes

import spock.lang.Specification

class KafkaConsumerTest extends Specification {
    def "automatic pattern detection"() {

        expect:
        topic.isPattern == isPattern

        where:
        topic                                           | isPattern
        new KafkaConsumer("369fine", "fake")            | false
        new KafkaConsumer("topic.main_command", "fake") | false
        new KafkaConsumer("totes.*regex", "fake")       | true
        new KafkaConsumer("gimme+some[^regex]", "fake") | true
    }
}
