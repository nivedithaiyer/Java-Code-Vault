package com.nivkart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nivkart.pojos.Order;

@Service
public class OrderService {
	
	List<Order> orders = new ArrayList<>();

	public List<Order> getAllOrders() {
        return orders;
    }
	
	public Order createOrder(Order order) {
		orders.add(order);
		return order;
	}
}
