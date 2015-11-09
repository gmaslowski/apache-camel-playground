package com.gmaslowski.cia.event;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class EventsRoute extends SpringRouteBuilder {

    private final EventRoutingProcessor eventRoutingProcessor;
    private final ResourceLoader resourceLoader;

    @Autowired
    EventsRoute(EventRoutingProcessor eventRoutingProcessor, ResourceLoader resourceLoader) {
        this.eventRoutingProcessor = eventRoutingProcessor;
        this.resourceLoader = resourceLoader;
    }


    @Override
    public void configure() throws Exception {
        from("file:" + resourceLoader.getResource("classpath:/messages").getFile().getAbsolutePath() + "?noop=true")
                .wireTap("jms:naiveAuditTrail")
                .bean(eventRoutingProcessor)
                .recipientList(header("recipients"));
    }
}
