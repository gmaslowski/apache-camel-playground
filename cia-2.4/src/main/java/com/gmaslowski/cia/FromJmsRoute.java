package com.gmaslowski.cia;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FromJmsRoute extends SpringRouteBuilder {
    @Override
    public void configure() throws Exception {
        from("jms:queue:filesIn")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.out.println(exchange.getIn().getHeaders());
                    }
                });
    }
}
