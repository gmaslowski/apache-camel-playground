package com.gmaslowski.cia;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Component;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;

@Configuration
@SpringBootApplication
public class JMSExample {

    public static void main(String[] args) {
        SpringApplication.run(JMSExample.class);
        while (true);
    }

    @Bean
    CamelContext camelContext(ApplicationContext context) {
        return new SpringCamelContext(context);
    }

    @Bean
    ActiveMQConnectionFactory activeMQConnectionFactory() {
        return new ActiveMQConnectionFactory("vm://localhost");
    }

    @Bean
    Component jms(ConnectionFactory activeMQConnectionFactory) {
        return JmsComponent.jmsComponentAutoAcknowledge(activeMQConnectionFactory);
    }
}
