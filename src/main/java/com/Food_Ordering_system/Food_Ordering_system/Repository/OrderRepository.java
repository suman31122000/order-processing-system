package com.Food_Ordering_system.Food_Ordering_system.Repository;

import com.Food_Ordering_system.Food_Ordering_system.Entity.Order;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class OrderRepository {
    private final Map<String, Order> orderMap = new HashMap<>();

    public void saveOrder(String userName, Order order) {
        orderMap.put(userName, order);
    }

    public Order findOrderById(Long orderId) {
        return orderMap.get(orderId);
    }
}
