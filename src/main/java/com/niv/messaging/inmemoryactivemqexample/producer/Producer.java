package com.niv.messaging.inmemoryactivemqexample.producer;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nivkart/order")
public class Producer {
  
  @Autowired
  private Queue queue;
  
  @Autowired
  private JmsTemplate jmsTemplate;
  
  @GetMapping("/{message}")
  public String sendOrderInformation(@PathVariable("message") String message) {
    jmsTemplate.convertAndSend(queue, message);
    return "Successfully published the message: "+message;
  }
}
