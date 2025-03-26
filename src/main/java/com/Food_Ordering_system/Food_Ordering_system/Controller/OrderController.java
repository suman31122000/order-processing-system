package com.Food_Ordering_system.Food_Ordering_system.Controller;

import com.Food_Ordering_system.Food_Ordering_system.Entity.Order;
import com.Food_Ordering_system.Food_Ordering_system.Service.OrderService;
import com.Food_Ordering_system.Food_Ordering_system.Service.ResturantService;
import com.Food_Ordering_system.Food_Ordering_system.Service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ResturantService resturantService;
    @Autowired
    ValidationService validationService;

    @PostMapping("/place")
    public ResponseEntity<String> placeOrder(@RequestParam String user,@RequestBody Map<String,Integer> items,@RequestParam String strategy) {
       validationService.validateOrderInput(user,items,strategy);
        return orderService.placeOrder(user,items,strategy);
    }

    @PostMapping("/complete/{orderId}")
    public ResponseEntity<String> completeOrder(@PathVariable Long orderId) {
        orderService.completeOrder(orderId);
        return ResponseEntity.ok("Order " + orderId + " marked as completed.");
    }

}
