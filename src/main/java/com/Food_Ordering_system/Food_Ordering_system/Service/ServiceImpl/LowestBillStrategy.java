package com.Food_Ordering_system.Food_Ordering_system.Service.ServiceImpl;

import com.Food_Ordering_system.Food_Ordering_system.Entity.Resturant;
import com.Food_Ordering_system.Food_Ordering_system.Service.OrderAssignmentService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
@Service
public class LowestBillStrategy implements OrderAssignmentService {
    @Override
    public Resturant selectRestaurant(List<Resturant> resturants, Map<String, Integer> items) {
        return resturants.stream()
                .filter(x -> x.canAcceptOrder(items))
                .min((r1, r2) -> Double.compare(r1.calculateOrderCost(items), r2.calculateOrderCost(items)))
                .orElse(null);
    }

}
