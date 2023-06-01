package com.niv.messaging.externalactivemqexample.producer;

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
	JmsTemplate jmsTemplate;
	
	@Autowired
	Queue queue;
	
	@GetMapping("/{productName}")
	 public String sendOrderInformation(@PathVariable("productName") String productName) {
		jmsTemplate.convertAndSend(queue, productName);
	    return "Successfully published the message: "+productName;
	  }
}
