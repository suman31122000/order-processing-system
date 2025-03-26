package com.Food_Ordering_system.Food_Ordering_system.Service.ServiceImpl;

import com.Food_Ordering_system.Food_Ordering_system.Entity.Resturant;
import com.Food_Ordering_system.Food_Ordering_system.Service.OrderAssignmentService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class HighestRatingStrategy implements OrderAssignmentService {
    @Override
    public Resturant selectRestaurant(List<Resturant> resturants, Map<String, Integer> items) {
        return resturants.stream()
                .filter(r -> r.canAcceptOrder(items))
                .max((a, b) -> Integer.compare(b.getRating(), a.getRating()))
                .orElse(null);
    }
}
