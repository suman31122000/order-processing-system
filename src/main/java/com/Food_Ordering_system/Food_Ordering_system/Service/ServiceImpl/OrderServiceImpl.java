package com.Food_Ordering_system.Food_Ordering_system.Service.ServiceImpl;

import com.Food_Ordering_system.Food_Ordering_system.Entity.Order;
import com.Food_Ordering_system.Food_Ordering_system.Entity.Resturant;
import com.Food_Ordering_system.Food_Ordering_system.Entity.User;
import com.Food_Ordering_system.Food_Ordering_system.Exception.InvalidOrderException;
import com.Food_Ordering_system.Food_Ordering_system.Exception.ResourceNotFound;
import com.Food_Ordering_system.Food_Ordering_system.Service.OrderAssignmentService;
import com.Food_Ordering_system.Food_Ordering_system.Service.OrderService;
import com.Food_Ordering_system.Food_Ordering_system.Service.ResturantService;
import com.Food_Ordering_system.Food_Ordering_system.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    private final Map<String,Order>orderMap=new HashMap<>();
    private final Map<String,OrderAssignmentService>assignmentServiceMap;
    private OrderAssignmentService strategy;
    private ResturantService resturantService;
    private UserService userService;

    public OrderServiceImpl(Map<String, OrderAssignmentService> assignmentServiceMap,
                            ResturantService resturantService,
                            UserService userService) {
        this.resturantService = resturantService;
        this.userService = userService;
        this.assignmentServiceMap = new LinkedHashMap<>();
        assignmentServiceMap.forEach((key, value) ->
                this.assignmentServiceMap.put(key.toLowerCase(), value)
        );
    }


    @Override
    public ResponseEntity<String> placeOrder(String userName, Map<String, Integer> items, String selectionStrategy) {
        List<Resturant> resturants = resturantService.getAllResturant();
        Map<String, User> allUsers = userService.getAllUsers();

        if (allUsers == null || !allUsers.containsKey(userName)) throw new ResourceNotFound("User not found please verify your details");
        strategy = assignmentServiceMap.getOrDefault(selectionStrategy.toLowerCase(), assignmentServiceMap.get("lowestbillstrategy"));
        if (strategy == null) throw new InvalidOrderException("verify your strategy and try again");

        Resturant selectedRestaurant = strategy.selectRestaurant(resturants, items);
        User user = allUsers.get(userName);

        if (selectedRestaurant == null) {
            user.setOrderStatus("Denied");
            return new ResponseEntity<>("Order cannot be placed. Please try again", HttpStatus.BAD_REQUEST);
        }

        Order order = new Order(user, items);
        orderMap.put(order.getUserName().getUserName(),order);
        order.setSelectionStrategy(selectionStrategy);
        order.setAssignedResturant(selectedRestaurant);

        return new ResponseEntity<>("Order assigned to "+selectedRestaurant.getName(), HttpStatus.ACCEPTED);
    }


    @Override
    public void completeOrder(Long orderId) {
        Order order = orderMap.get(orderId);
        if (order != null) {
            order.markCompleted();
        }
    }
}
