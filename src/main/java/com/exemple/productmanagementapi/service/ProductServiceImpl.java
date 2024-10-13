package com.exemple.productmanagementapi.service;

import com.exemple.productmanagementapi.exception.ApiException;
import com.exemple.productmanagementapi.model.Product;
import com.exemple.productmanagementapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.http.HttpStatus;

 @Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ApiException("Product not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Product updateProduct(String id, Product product) {
        Product existingProduct = getProductById(id);
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setUserId(product.getUserId());
        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(String id) {
        if (!productRepository.existsById(id)) {
            throw new ApiException("Product not found", HttpStatus.NOT_FOUND);
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByUserId(String userId) {
        List<Product> products = productRepository.findByUserId(userId);
        if (products.isEmpty()) {
            throw new ApiException("No products found for this user", HttpStatus.NOT_FOUND);
        }
        return products;
    }
}