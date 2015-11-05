package com.gmaslowski.cia;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class FileRouterExample {

    public static void main(String args[]) throws Exception {
        CamelContext context = new DefaultCamelContext();

        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file:/tmp/cia-1.2/input?noop=true")
                        .to("file:/tmp/cia-1.2/output");
            }
        });

        context.start();

        while(true) {}
    }
}
