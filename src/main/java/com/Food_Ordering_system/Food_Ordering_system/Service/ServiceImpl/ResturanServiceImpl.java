package com.Food_Ordering_system.Food_Ordering_system.Service.ServiceImpl;

import com.Food_Ordering_system.Food_Ordering_system.Entity.Resturant;
import com.Food_Ordering_system.Food_Ordering_system.Exception.ResourceNotFound;
import com.Food_Ordering_system.Food_Ordering_system.Service.ResturantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ResturanServiceImpl implements ResturantService{
    private final Map<String,Resturant>resturantsMap=new HashMap<>();
    @Override
    public ResponseEntity<String> createResturant(Resturant resturant) {
        String name=resturant.getName();
        if(resturantsMap.containsKey(name)) return new ResponseEntity<>("Resturant with this name is already present",HttpStatus.BAD_REQUEST);

        resturantsMap.put(name,resturant);
        return new ResponseEntity<>("Restaurant Created Successfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> addMenu(String name, String item,double price) {
        if(!resturantsMap.containsKey(name)) throw new ResourceNotFound("please select the valid resturant");
        Map<String, Double> menu = resturantsMap.get(name).getMenu();
        if (menu == null) resturantsMap.get(name).setMenu(new HashMap<>());
        resturantsMap.get(name).getMenu().put(item,price);
        return new ResponseEntity<>("Menu item added successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> incPrice(String name, String item,double price){
        if(!resturantsMap.containsKey(name)) throw new ResourceNotFound("please select the valid resturant");
        Map<String, Double> menu = resturantsMap.get(name).getMenu();
        if(!menu.containsKey(item)) throw new ResourceNotFound("there is no items in the menu");
        menu.replace(item,price);
        return new ResponseEntity<>("Menu item price is updated",HttpStatus.OK);
    }

    @Override
    public List<Resturant> getAllResturant() {
        if(resturantsMap.isEmpty()) throw new RuntimeException("there is no resturant");
        return new ArrayList<>(resturantsMap.values());
    }

}
