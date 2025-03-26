package com.Food_Ordering_system.Food_Ordering_system.Service;

import com.Food_Ordering_system.Food_Ordering_system.Entity.User;
import java.util.Map;

public interface ValidationService {
    void validateRestaurantInput(String name, int maxOrders, int rating);
    void validateOrderInput(String user, Map<String, Integer> items, String strategy);
    void validateMenuUpdate(String name, String item, int price);
    void validateUserInput(User user);
}