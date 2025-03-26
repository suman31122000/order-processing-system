package com.Food_Ordering_system.Food_Ordering_system.Service.ServiceImpl;

import com.Food_Ordering_system.Food_Ordering_system.Service.ValidationService;

import com.Food_Ordering_system.Food_Ordering_system.Entity.User;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Override
    public void validateRestaurantInput(String name, int maxOrders, int rating) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Restaurant name cannot be blank.");
        }
        if (maxOrders < 1) {
            throw new IllegalArgumentException("Max number of orders should be at least 1.");
        }
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }
    }

    @Override
    public void validateOrderInput(String user, Map<String, Integer> items, String strategy) {
        if (user == null || user.trim().isEmpty()) {
            throw new IllegalArgumentException("User name cannot be blank.");
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Order items cannot be empty.");
        }
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            entry.getKey().toLowerCase();
            if (entry.getValue() <= 0) {
                throw new IllegalArgumentException("Each item quantity must be greater than 0.");
            }
        }
        if (strategy == null || strategy.trim().isEmpty()) {
            throw new IllegalArgumentException("Selection strategy cannot be blank.");
        }
        strategy.toLowerCase();
    }

    @Override
    public void validateMenuUpdate(String name, String item, int price) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Restaurant name cannot be blank.");
        }
        if (item == null || item.trim().isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be blank.");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Item price must be greater than 0.");
        }
        item.toLowerCase();
    }

    @Override
    public void validateUserInput(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }
        if (user.getUserName() == null || user.getUserName().trim().isEmpty()) {
            throw new IllegalArgumentException("User name cannot be blank.");
        }
    }
}