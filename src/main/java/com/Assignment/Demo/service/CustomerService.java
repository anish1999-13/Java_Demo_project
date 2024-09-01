package com.Assignment.Demo.service;

import com.Assignment.Demo.exception.CustomerNotFoundException;
import com.Assignment.Demo.models.Customer;

public interface CustomerService {

    Customer registerCustomer(Customer customer);

    Customer updateCustomer(Customer customer) throws CustomerNotFoundException;
}
