package com.Food_Ordering_system.Food_Ordering_system.Repository;

import com.Food_Ordering_system.Food_Ordering_system.Entity.Resturant;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class RestaurantRepository {
    private final Map<String, Resturant> resturantsMap = new HashMap<>();

    public void save(Resturant resturant) {
        resturantsMap.put(resturant.getName(), resturant);
    }

    public Resturant findByName(String name) {
        return resturantsMap.get(name);
    }

    public boolean existsByName(String name) {
        return resturantsMap.containsKey(name);
    }

    public List<Resturant> findAll() {
        return new ArrayList<>(resturantsMap.values());
    }
}
