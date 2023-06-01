package com.niv.messaging.externalactivemqexample.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	@JmsListener(destination="standalone.orderqueue")
	 public void processMessage(String message) {
	    System.out.println("New Message Received -- \n"+message);
	  }
}
