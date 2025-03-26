package com.Food_Ordering_system.Food_Ordering_system.Service;

import com.Food_Ordering_system.Food_Ordering_system.Entity.Resturant;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ResturantService {
  public  ResponseEntity<String>createResturant(Resturant resturant);
  public ResponseEntity<String>addMenu(String name, String item, double price);
  public ResponseEntity<String>incPrice(String name,String item,double price);
  public List<Resturant> getAllResturant();
}
