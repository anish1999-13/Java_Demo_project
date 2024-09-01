package com.Assignment.Demo.service.impl;

import com.Assignment.Demo.exception.ProductNotFoundException;
import com.Assignment.Demo.models.Product;
import com.Assignment.Demo.repository.ProductRepository;
import com.Assignment.Demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product createProduct(Product product) {
        Product saved= productRepository.save(product);
        return saved;
    }

    @Override
    public List<Product> retrieveProductByWord(String searchWord) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(searchWord);
        return products;
    }

    @Override
    public Product stockProduct(Long productId, Long newStock) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product not found");
        }
        Product product = productOptional.get();
        product.setAvailableQuantity(newStock+product.getAvailableQuantity());
        return productRepository.save(product);
    }
}
