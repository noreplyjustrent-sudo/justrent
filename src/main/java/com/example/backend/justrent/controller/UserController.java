package com.example.backend.justrent.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.justrent.dto.userResponse;
import com.example.backend.justrent.model.User;
import com.example.backend.justrent.services.UserService;
@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        System.out.println(authentication);
        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<userResponse>> allUsers() {
        List<userResponse> users = userService.allUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<userResponse> getUserById(@PathVariable String id) {
        Optional<userResponse> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/favorites/add")
    public ResponseEntity<String> addRentalToFavorites(
            @RequestParam String userId,
            @RequestParam String rentalId) {
        String result = userService.addRentalToFavorites(userId, rentalId);
        return ResponseEntity.ok(result);
    }

}