package com.exemple.productmanagementapi.service;

import com.exemple.productmanagementapi.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(String id);
    User updateUser(String id, User user);
    void deleteUser(String id);
    List<User> getAllUsers();
    User findByEmail(String email);
}