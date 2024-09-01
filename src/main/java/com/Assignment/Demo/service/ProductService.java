package com.Assignment.Demo.service;

import com.Assignment.Demo.exception.ProductNotFoundException;
import com.Assignment.Demo.models.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

    List<Product> retrieveProductByWord(String searchWord);

    Product stockProduct(Long productId, Long newStock) throws ProductNotFoundException;
}
