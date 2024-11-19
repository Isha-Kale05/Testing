package com.example.online.exam.system;

import com.example.online.exam.system.*;
import com.example.online.exam.system.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        // You can add logic for hashing the password here, if necessary
        return userRepository.save(user);
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email); // Fetch user by email
    }

	
}