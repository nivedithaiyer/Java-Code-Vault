package com.nivkart.service;

import org.springframework.jms.annotation.JmsListener;

public class NotificationService {

    @JmsListener(destination = "OrderStatusUpdates", containerFactory = "jmsListenerContainerFactory")
    public void processOrderStatusUpdates() {
      // Process the order status updates and send notifications to customers
      // One can send an email to the user
      // Logic to send a notification
      // Replace this with your actual implementation
      System.out.println("Processing order status updates");
    }
}

