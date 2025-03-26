package com.Food_Ordering_system.Food_Ordering_system.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class Resturant {
    private final String name;
    private final int maxNoOfOrders;
    private Map<String, Double> menu;
    private int rating;
    private int assignedOrders;

    public Resturant(String name, int maxNoOfOrders,int rating) {
        this.name = name;
        this.maxNoOfOrders = maxNoOfOrders;
        this.rating=rating;
    }

    public boolean canAcceptOrder(Map<String, Integer> items) {
        if ( assignedOrders < maxNoOfOrders) {
            return true;
        }
        return false;
    }

    public double calculateOrderCost(Map<String, Integer> items) {
        double bill = 0.0;
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            if (menu != null && menu.containsKey(itemName)) {
                bill += menu.get(itemName) * quantity;
            }
        }
        return bill;
    }
    public int getAssignedOrders() {
        assignedOrders++;
        return assignedOrders;
    }
    public void completeAssignedOrder(){
        assignedOrders--;
    }
}
