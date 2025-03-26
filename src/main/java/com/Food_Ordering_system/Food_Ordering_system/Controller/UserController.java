package com.Food_Ordering_system.Food_Ordering_system.Controller;

import com.Food_Ordering_system.Food_Ordering_system.Entity.User;
import com.Food_Ordering_system.Food_Ordering_system.Service.UserService;
import com.Food_Ordering_system.Food_Ordering_system.Service.ValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final ValidationService validationService;

    public UserController(UserService userService, ValidationService validationService) {
        this.userService = userService;
        this.validationService = validationService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        validationService.validateUserInput(user);
        userService.addUser(user);
        return ResponseEntity.ok("User added successfully.");
    }

    @DeleteMapping("/delete/{userName}")
    public ResponseEntity<String> deleteUser(@PathVariable String userName) {
        userService.deleteUser(userName);
        return ResponseEntity.ok("User " + userName + " deleted successfully.");
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
