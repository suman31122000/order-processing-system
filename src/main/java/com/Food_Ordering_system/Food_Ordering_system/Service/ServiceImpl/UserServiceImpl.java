package com.Food_Ordering_system.Food_Ordering_system.Service.ServiceImpl;

import com.Food_Ordering_system.Food_Ordering_system.Entity.User;
import com.Food_Ordering_system.Food_Ordering_system.Exception.ResourceNotFound;
import com.Food_Ordering_system.Food_Ordering_system.Service.UserService;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private final Map<String, User> userMap = new HashMap<>();

    @Override
    public void addUser(User user) {
        userMap.put(user.getUserName(), user);
    }

    @Override
    public void deleteUser(String name) {
        if (!userMap.containsKey(name)) throw new ResourceNotFound("user with this name is not found");
        userMap.remove(name);
    }
    @Override
    public Map<String,User> getAllUsers() {
        return userMap;
    }
}
