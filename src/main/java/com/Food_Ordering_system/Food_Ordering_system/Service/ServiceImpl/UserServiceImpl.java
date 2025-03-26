package com.Food_Ordering_system.Food_Ordering_system.Service.ServiceImpl;

import com.Food_Ordering_system.Food_Ordering_system.Entity.User;
import com.Food_Ordering_system.Food_Ordering_system.Exception.ResourceNotFound;
import com.Food_Ordering_system.Food_Ordering_system.Repository.UserRepository;
import com.Food_Ordering_system.Food_Ordering_system.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String name) {
        if (!userRepository.existsByName(name)) throw new ResourceNotFound("User not found");
        userRepository.delete(name);
    }

    @Override
    public Map<String, User> getAllUsers() {
        return userRepository.findAll();
    }
}
