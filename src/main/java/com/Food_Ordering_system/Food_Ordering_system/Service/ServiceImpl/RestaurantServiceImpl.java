package com.Food_Ordering_system.Food_Ordering_system.Service.ServiceImpl;

import com.Food_Ordering_system.Food_Ordering_system.Entity.Resturant;
import com.Food_Ordering_system.Food_Ordering_system.Exception.ResourceNotFound;
import com.Food_Ordering_system.Food_Ordering_system.Repository.RestaurantRepository;
import com.Food_Ordering_system.Food_Ordering_system.Service.ResturantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RestaurantServiceImpl implements ResturantService {
    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public ResponseEntity<String> createResturant(Resturant resturant) {
        if (restaurantRepository.existsByName(resturant.getName())) {
            return new ResponseEntity<>("Restaurant with this name is already present", HttpStatus.BAD_REQUEST);
        }
        restaurantRepository.save(resturant);
        return new ResponseEntity<>("Restaurant Created Successfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> addMenu(String name, String item, double price) {
        Resturant restaurant = restaurantRepository.findByName(name);
        if (restaurant == null) throw new ResourceNotFound("Please select a valid restaurant");

        if (restaurant.getMenu() == null) {
            restaurant.setMenu(new HashMap<>());
        }

        restaurant.getMenu().put(item, price);
        return new ResponseEntity<>("Menu item added successfully", HttpStatus.OK);
    }


    @Override
    public ResponseEntity<String> incPrice(String name, String item, double price) {
        Resturant restaurant = restaurantRepository.findByName(name);
        if (restaurant == null) throw new ResourceNotFound("Please select a valid restaurant");

        if (restaurant.getMenu() == null) {
            throw new ResourceNotFound("Menu is empty, add items first.");
        }

        Map<String, Double> menu = restaurant.getMenu();
        if (!menu.containsKey(item)) throw new ResourceNotFound("There is no such item in the menu");

        menu.replace(item, price);
        return new ResponseEntity<>("Menu item price is updated", HttpStatus.OK);
    }


    @Override
    public List<Resturant> getAllResturant() {
        return restaurantRepository.findAll();
    }
}
