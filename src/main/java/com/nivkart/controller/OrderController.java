package com.nivkart.controller;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nivkart.pojos.Order;
import com.nivkart.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private static final String BROKER_URL = "tcp://localhost:61616";
    private static final String QUEUE_NAME = "orders-queue";
    
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
    
    @GetMapping("/{orderId}/{productName}")
    public void createAnOrder(@PathVariable(name = "orderId") String orderId, @PathVariable(name = "productName") String productName) {
        try {
            // Create a connection factory
        	ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
            connectionFactory.setTrustAllPackages(true);
            // Create a connection
            javax.jms.Connection connection = connectionFactory.createConnection();
            connection.start();

            // Create a session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create a destination (queue)
            javax.jms.Destination destination = session.createQueue(QUEUE_NAME);

            // Create a message producer
            MessageProducer producer = session.createProducer(destination);

            Order order = new Order();
            order.setOrderId(orderId);
            order.setProductName(productName);
            orderService.createOrder(order);
            MapMessage message = session.createMapMessage();
            message.setString("productName", productName);
            message.setString("id", orderId);
            
            // Send the message
            producer.send(message);

            // Clean up resources
            producer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
