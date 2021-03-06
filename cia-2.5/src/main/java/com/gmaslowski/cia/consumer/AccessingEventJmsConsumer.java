package com.gmaslowski.cia.consumer;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.spring.SpringRouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AccessingEventJmsConsumer extends SpringRouteBuilder {
    private static Logger log = LoggerFactory.getLogger(AccessingEventJmsConsumer.class);

    @Value("${accessingEventJmsQueue}")
    private String accessingEventJmsQueue;

    @Override
    public void configure() throws Exception {
        from(accessingEventJmsQueue)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        String filePath = exchange.getIn().getHeader("CamelFileAbsolutePath").toString();
                        log.info("Incoming file: " + filePath.substring(filePath.lastIndexOf("/")));
                    }
                });
    }
}
