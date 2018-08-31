package routes

import spock.lang.Specification

class TopicListenerTest extends Specification {
    def "automatic pattern detection"() {

        expect:
        topic.isPattern == isPattern

        where:
        topic                                         | isPattern
        new TopicListener("369fine")            | false
        new TopicListener("topic.main_command") | false
        new TopicListener("totes.*regex")       | true
        new TopicListener("gimme+some[^regex]") | true
    }
}
