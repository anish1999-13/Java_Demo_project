package com.Assignment.Demo.controller;

import com.Assignment.Demo.exception.ProductNotFoundException;
import com.Assignment.Demo.models.Product;
import com.Assignment.Demo.repository.ProductRepository;
import com.Assignment.Demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product){

        Product savedProduct=productService.createProduct(product);
        return savedProduct;
    }


    // API 1- gets all the products whose names contains the search words

    @GetMapping("search/{searchword}")
    public List<Product> retrieveProductByWord(@PathVariable String searchword){
        List<Product> retreivedProduct=productService.retrieveProductByWord(searchword);
        return retreivedProduct;
    }

    // API 2- stock a product - based on product id

    @PostMapping("/stock/{productId}")
    public Product stockProduct(@PathVariable Long productId, @RequestParam Long newstock) throws ProductNotFoundException {
        Product updatedProduct = productService.stockProduct(productId, newstock);
        return updatedProduct;
    }

}
