package com.Assignment.Demo.service.impl;

import com.Assignment.Demo.exception.CustomerNotFoundException;
import com.Assignment.Demo.models.Customer;
import com.Assignment.Demo.repository.CustomerRepository;
import com.Assignment.Demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Customer registerCustomer(Customer customer) {
        Customer saved= customerRepository.save(customer);
        return saved;
    }

    @Override
    public Customer updateCustomer(Customer customer) throws CustomerNotFoundException {

        Long customerId= customer.getCustomerId();
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if(customerOptional.isEmpty())
        {
            throw new CustomerNotFoundException("No Customer found with Id"+customerId);
        }
        Customer existingCustomer=customerOptional.get();
        if (customer.getFirstName() != null) {
            existingCustomer.setFirstName(customer.getFirstName());
        }
        if (customer.getLastName() != null) {
            existingCustomer.setLastName(customer.getLastName());
        }
        if (customer.getEmail() != null) {
            existingCustomer.setEmail(customer.getEmail());
        }
        if (customer.getPhoneNumber() != null) {
            existingCustomer.setPhoneNumber(customer.getPhoneNumber());
        }
        return customerRepository.save(existingCustomer);
    }
}
