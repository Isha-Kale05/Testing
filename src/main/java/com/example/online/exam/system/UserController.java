package com.example.online.exam.system;

import com.example.online.exam.system.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        // Validate if passwords match (in case it is needed)
        if (!user.getPassword().equals(user.getPassword())) {
            return ResponseEntity.badRequest().body("Passwords do not match");
        }

        // Call the service to save the user
        User registeredUser = userService.registerUser(user);

        // Redirect logic based on user type (for frontend purposes)
        if (user.getUserType().equals("admin")) {
            return ResponseEntity.ok("Registration successful! Redirecting to Admin Dashboard...");
        } else if (user.getUserType().equals("student")) {
            return ResponseEntity.ok("Registration successful! Redirecting to Student Dashboard...");
        }

        return ResponseEntity.badRequest().body("Invalid user type");
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody User user) {
        // Fetch user by email
        User existingUser = userService.getUserByEmail(user.getEmail());

        Map<String, String> response = new HashMap<>();

        if (existingUser == null) {
            response.put("message", "User not found");
            return ResponseEntity.badRequest().body(response);  // Return as JSON
        }

        // Check if the password matches
        if (!existingUser.getPassword().equals(user.getPassword())) {
            response.put("message", "Invalid password");
            return ResponseEntity.badRequest().body(response);  // Return as JSON
        }

        // Check if user type matches
        if (!existingUser.getUserType().equals(user.getUserType())) {
            response.put("message", "Invalid user type");
            return ResponseEntity.badRequest().body(response);  // Return as JSON
        }

        // Login successful
        response.put("message", "Login successful! Redirecting to " + existingUser.getUserType() + " Dashboard...");
        return ResponseEntity.ok(response);  // Return as JSON
    }

}