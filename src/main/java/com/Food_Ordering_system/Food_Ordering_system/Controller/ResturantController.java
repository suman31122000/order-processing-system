package com.Food_Ordering_system.Food_Ordering_system.Controller;
import com.Food_Ordering_system.Food_Ordering_system.Entity.Resturant;
import com.Food_Ordering_system.Food_Ordering_system.Service.ResturantService;
import com.Food_Ordering_system.Food_Ordering_system.Service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
public class ResturantController {
    @Autowired
    private ResturantService resturantService;
    @Autowired
    private ValidationService validationService;

    @PostMapping("/onboard")
    public ResponseEntity<String> onboardRestaurant(@RequestParam String name,@RequestParam int MaxNoOfOrders,@RequestParam int rating) {
        validationService.validateRestaurantInput(name,MaxNoOfOrders,rating);
        Resturant resturant=new Resturant(name,MaxNoOfOrders,rating);
        return resturantService.createResturant(resturant);
    }

    @PostMapping("/updateMenu")
    public ResponseEntity<String> updateMenu(@RequestParam String name, @RequestParam String item, @RequestParam int price) {
       validationService.validateMenuUpdate(name,item,price);
        return resturantService.addMenu(name, item, price);
    }
    @PostMapping("increase-price")
    public ResponseEntity<String> IncreasePrice(String name,String item,Double price){
        return resturantService.incPrice(name,item,price);

    }
    @GetMapping
    public ResponseEntity<?>getAllResturant(){
        List<Resturant> allResturant = resturantService.getAllResturant();
        System.out.println(allResturant);
        return new ResponseEntity<>(allResturant,HttpStatus.OK);

    }
}
