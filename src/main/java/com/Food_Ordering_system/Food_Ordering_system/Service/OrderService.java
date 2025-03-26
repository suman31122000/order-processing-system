package com.Food_Ordering_system.Food_Ordering_system.Service;

import com.Food_Ordering_system.Food_Ordering_system.Entity.Order;
import com.Food_Ordering_system.Food_Ordering_system.Entity.Resturant;
import com.Food_Ordering_system.Food_Ordering_system.Entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface OrderService {

     ResponseEntity<String> placeOrder(String userName, Map<String, Integer> items, String selectionStrategy);
     void completeOrder(Long order);
}
