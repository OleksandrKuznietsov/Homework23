package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRepository {
    private Map<Integer, Order> orders = new HashMap<>();
    private int currentId = 1;

    public Order getOrderById(int orderId){
        return orders.get(orderId);
    }

    public List<Order> getAllOrders(){
        return new ArrayList<>(orders.values());
    }

    public void addOrder(Order order){
        order.setId(currentId++);
        orders.put(order.getId(), order);
    }
}
