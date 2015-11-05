package com.gmaslowski.cia;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FromFileToJmsRoute extends SpringRouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:/tmp/cia-2.4/input")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.out.println(exchange.getIn().getHeaders());
                    }
                })
                .to("jms:queue:filesIn");
    }
}
