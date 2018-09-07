package routes

import spock.lang.Specification

class TopicListenerTest extends Specification {
    def "automatic pattern detection"() {

        expect:
        topic.isPattern == isPattern

        where:
        topic                                           | isPattern
        new TopicListener("369fine", "fake")            | false
        new TopicListener("topic.main_command", "fake") | false
        new TopicListener("totes.*regex", "fake")       | true
        new TopicListener("gimme+some[^regex]", "fake") | true
    }
}
