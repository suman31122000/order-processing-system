package com.Food_Ordering_system.Food_Ordering_system.Service.ServiceImpl;

import com.Food_Ordering_system.Food_Ordering_system.Entity.Order;
import com.Food_Ordering_system.Food_Ordering_system.Entity.Resturant;
import com.Food_Ordering_system.Food_Ordering_system.Entity.User;
import com.Food_Ordering_system.Food_Ordering_system.Exception.InvalidOrderException;
import com.Food_Ordering_system.Food_Ordering_system.Exception.ResourceNotFound;
import com.Food_Ordering_system.Food_Ordering_system.Repository.OrderRepository;
import com.Food_Ordering_system.Food_Ordering_system.Repository.UserRepository;
import com.Food_Ordering_system.Food_Ordering_system.Repository.RestaurantRepository;
import com.Food_Ordering_system.Food_Ordering_system.Service.OrderAssignmentService;
import com.Food_Ordering_system.Food_Ordering_system.Service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final Map<String, OrderAssignmentService> assignmentServiceMap;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository,
                            RestaurantRepository restaurantRepository, Map<String, OrderAssignmentService> assignmentServiceMap) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.assignmentServiceMap = new HashMap<>();
        if (assignmentServiceMap != null) {
            assignmentServiceMap.forEach((key, value) ->
                    this.assignmentServiceMap.put(key.toLowerCase(), value)
            );
        }
    }


    @Override
    public ResponseEntity<String> placeOrder(String userName, Map<String, Integer> items, String selectionStrategy) {
        List<Resturant> resturants = restaurantRepository.findAll();
        Map<String, User> allUsers = userRepository.findAll();

        if (!allUsers.containsKey(userName)) throw new ResourceNotFound("User not found, please verify your details");

        OrderAssignmentService strategy = assignmentServiceMap.getOrDefault(selectionStrategy.toLowerCase(), assignmentServiceMap.get("lowestbillstrategy"));
        if (strategy == null) throw new InvalidOrderException("Verify your strategy and try again");

        Resturant selectedRestaurant = strategy.selectRestaurant(resturants, items);
        User user = allUsers.get(userName);

        if (selectedRestaurant == null) {
            user.setOrderStatus("Denied");
            return new ResponseEntity<>("Order cannot be placed. Please try again", HttpStatus.BAD_REQUEST);
        }

        Order order = new Order(user, items);
        order.setSelectionStrategy(selectionStrategy);
        selectedRestaurant.getAssignedOrders();
        order.setAssignedResturant(selectedRestaurant);
        orderRepository.saveOrder(userName, order);

        return new ResponseEntity<>("Order assigned to " + selectedRestaurant.getName(), HttpStatus.ACCEPTED);
    }

    @Override
    public void completeOrder(Long orderId) {
        Order order = orderRepository.findOrderById(orderId);
        if (order != null) {
            order.markCompleted();
        }
    }
}
