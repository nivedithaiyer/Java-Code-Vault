package com.nivkart.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nivkart.pojos.Item;
import com.nivkart.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public Set<Item> getAllInventory() {
    	return inventoryService.getAllInventory();
    }
    
    @GetMapping("/{productName}")
    public Item checkInventory(@PathVariable(name="productName") String productName ) {
        // Check inventory and return the status
        int availableItems = inventoryService.getAvailableItems(productName);
        Item item = new Item();
        item.setProductName(productName);
        item.setQuantity(availableItems);
        return item;
    }
}
