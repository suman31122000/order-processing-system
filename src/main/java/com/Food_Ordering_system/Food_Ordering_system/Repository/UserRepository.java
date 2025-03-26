package com.Food_Ordering_system.Food_Ordering_system.Repository;

import com.Food_Ordering_system.Food_Ordering_system.Entity.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {
    private final Map<String, User> userMap = new HashMap<>();

    public void save(User user) {
        userMap.put(user.getUserName(), user);
    }

    public void delete(String name) {
        userMap.remove(name);
    }

    public boolean existsByName(String name) {
        return userMap.containsKey(name);
    }

    public Map<String, User> findAll() {
        return userMap;
    }
}
