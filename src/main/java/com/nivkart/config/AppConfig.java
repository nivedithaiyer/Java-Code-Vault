package com.nivkart.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import com.nivkart.service.InventoryService;
import com.nivkart.service.NotificationService;

@Configuration
@ComponentScan("com.nivkart.controller")
@ComponentScan("com.nivkart.service")
public class AppConfig {

    @Bean
    public InventoryService inventoryService(JmsTemplate jmsTemplate) {
        return new InventoryService(jmsTemplate);
    }

    @Bean
    public NotificationService notificationService() {
        return new NotificationService(); // Instantiate the service bean
    }
    
    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory);
        // Set any additional properties for the JmsTemplate

        return jmsTemplate;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL("tcp://localhost:61616");
        connectionFactory.setTrustAllPackages(true);
        // Set any additional properties for the ConnectionFactory

        return connectionFactory;
    }
}
