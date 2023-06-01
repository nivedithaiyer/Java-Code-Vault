package com.nivkart.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

import com.nivkart.pojos.Item;

public class InventoryService {

    private int defaultAvailableItems = 10;
    private final JmsTemplate jmsTemplate;
    private Map<String, Integer> productVsInventoryMap = new HashMap<>();
    Set<Item> items = new HashSet<>();
    
    @Autowired
    public InventoryService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public int getAvailableItems(String productName) {
    	if(productVsInventoryMap.containsKey(productName)) {
    		return productVsInventoryMap.get(productName);
    	}
    	
    	return defaultAvailableItems;
    }

    @JmsListener(destination = "orders-queue")
    public void processOrder(Map<String, String> orderMap) {
        // Process the order and update the inventory accordingly
        // Replace this with your actual implementation
        String productName = orderMap.get("productName");
		int availableItems = defaultAvailableItems;
        if(productVsInventoryMap.containsKey(productName)) {
        	availableItems = productVsInventoryMap.get(productName);
        	productVsInventoryMap.put(productName, availableItems);
        }
        
        availableItems--;
    	productVsInventoryMap.put(productName, availableItems);
      
        Item item = new Item();
        item.setProductName(productName);
        item.setQuantity(availableItems);
        createItem(item);
        // Push the available inventory details to the OrderStatusUpdates topic
        jmsTemplate.convertAndSend("OrderStatusUpdates", item);
    }

    public Set<Item> getAllInventory() {
        return items;
    }
	
	public Item createItem(Item item) {
		items.add(item);
		return item;
	}
}


