package com.Assignment.Demo.repository;

import com.Assignment.Demo.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByNameContainingIgnoreCase(String searchWord);

}
