package com.exemple.productmanagementapi.service;

import com.exemple.productmanagementapi.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product getProductById(String id);
    Product updateProduct(String id, Product product);
    void deleteProduct(String id);
    List<Product> getAllProducts();
    List<Product> getProductsByUserId(String userId);
}