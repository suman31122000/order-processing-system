package com.Food_Ordering_system.Food_Ordering_system.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Order {
    private static final AtomicInteger idCounter = new AtomicInteger(0);
    private int id;
    private User userName;
    private Map<String, Integer> items;
    private Resturant assignedResturant;
    private String selectionStrategy;
    private boolean completed = false;

    public Order(User userName, Map<String, Integer> items) {
        this.id = idCounter.incrementAndGet();
        this.userName = userName;
        this.items = items;
    }

    public void markCompleted() {
        this.completed = true;
        if (assignedResturant.getAssignedOrders()>0) {
            userName.setOrderStatus("Completed");
            assignedResturant.completeAssignedOrder();
        }
    }
}
