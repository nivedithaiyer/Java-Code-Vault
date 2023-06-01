package com.niv.messaging.inmemoryactivemqexample.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.niv.messaging.inmemoryactivemqexample.config.Config;

@Component
public class Consumer {

  @JmsListener(destination = Config.ORDER_QUEUE)
  public void processMessage(String message) {
    System.out.println("New Message Received -- \n"+message);
  }
}
