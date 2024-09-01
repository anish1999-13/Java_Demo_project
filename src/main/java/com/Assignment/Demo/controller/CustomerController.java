package com.Assignment.Demo.controller;


import com.Assignment.Demo.exception.CustomerNotFoundException;
import com.Assignment.Demo.models.Customer;
import com.Assignment.Demo.repository.CustomerRepository;
import com.Assignment.Demo.service.CustomerService;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @PostMapping("/register")
    public Customer registerCustomer(@RequestBody Customer customer){
        Customer savedCustomer = customerService.registerCustomer(customer);
        return savedCustomer;
    }

    //update the customer details

    @PutMapping("/update")
    public Customer updateCustomer(@RequestBody Customer customer) throws CustomerNotFoundException {
        Customer updatedCustomer = customerService.updateCustomer(customer);
        return updatedCustomer;
    }
}
