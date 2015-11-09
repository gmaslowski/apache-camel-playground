package com.gmaslowski.cia.event;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

@Component
public class EventsRoute extends SpringRouteBuilder {

    @Value("${firstJmsQueue}")
    private String firstJmsQueue;

    @Value("${secondJmsQueue}")
    private String secondJmsQueue;

    private final ResourceLoader resourceLoader;
    private final ExecutorService executor;

    @Autowired
    EventsRoute(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        executor = newFixedThreadPool(2);
    }

    @Override
    public void configure() throws Exception {
        from("file:" + resourceLoader.getResource("classpath:/messages").getFile().getAbsolutePath() + "?noop=true")
                .multicast()
                .parallelProcessing().executorService(executor)
                .to(firstJmsQueue, secondJmsQueue);
    }
}
