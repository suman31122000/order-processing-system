package com.Food_Ordering_system.Food_Ordering_system.Service;

import com.Food_Ordering_system.Food_Ordering_system.Entity.User;
import java.util.Map;

public interface UserService {
    void addUser(User user);
    void deleteUser(String name);
    Map<String,User> getAllUsers();
}

