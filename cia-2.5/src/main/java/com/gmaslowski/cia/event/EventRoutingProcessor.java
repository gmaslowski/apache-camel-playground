package com.gmaslowski.cia.event;

import org.apache.camel.RecipientList;
import org.apache.camel.language.XPath;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventRoutingProcessor implements EventType.EventTypeVisitor<String[]> {

    @Value("${modifyingEventJmsQueue}")
    private String modifyingEventJmsQueue;

    @Value("${listingEventJmsQueue}")
    private String listingEventJmsQueue;

    @Value("${deletingEventJmsQueue}")
    private String deletingEventJmsQueue;

    @Value("${accessingEventJmsQueue}")
    private String accessingEventJmsQueue;

    @RecipientList
    public String[] route(@XPath("/event/@type") String eventType) {
        return EventType.valueOf(eventType.toUpperCase()).accept(this);
    }

    @Override
    public String[] visitCreate() {
        return new String[]{modifyingEventJmsQueue};
    }

    @Override
    public String[] visitUpdate() {
        return new String[]{modifyingEventJmsQueue, accessingEventJmsQueue};
    }

    @Override
    public String[] visitList() {
        return new String[]{listingEventJmsQueue, accessingEventJmsQueue};
    }

    @Override
    public String[] visitDelete() {
        return new String[]{deletingEventJmsQueue};
    }
}


