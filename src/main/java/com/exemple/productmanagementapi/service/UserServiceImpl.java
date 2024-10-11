package com.exemple.productmanagementapi.service;

import com.exemple.productmanagementapi.exception.ApiException;
import com.exemple.productmanagementapi.model.User;
import com.exemple.productmanagementapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new ApiException("Email already in use", HttpStatus.BAD_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ApiException("User not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public User updateUser(String id, User user) {
        User existingUser = getUserById(id);
        existingUser.setName(user.getName());
        if (!existingUser.getEmail().equals(user.getEmail()) && userRepository.findByEmail(user.getEmail()) != null) {
            throw new ApiException("Email already in use", HttpStatus.BAD_REQUEST);
        }
        existingUser.setEmail(user.getEmail());
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        existingUser.setRole(user.getRole());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            throw new ApiException("User not found", HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ApiException("User not found", HttpStatus.NOT_FOUND);
        }
        return user;
    }
}