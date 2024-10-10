package com.exemple.productmanagementapi.repository;

import com.exemple.productmanagementapi.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}