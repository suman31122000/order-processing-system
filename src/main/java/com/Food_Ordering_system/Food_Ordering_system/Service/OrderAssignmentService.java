package com.Food_Ordering_system.Food_Ordering_system.Service;
import com.Food_Ordering_system.Food_Ordering_system.Entity.Resturant;

import java.util.List;
import java.util.Map;

public interface OrderAssignmentService {
    Resturant selectRestaurant(List<Resturant> resturants,Map<String,Integer>items);
}
