package com.Assignment.Demo.service;

import com.Assignment.Demo.exception.CustomerNotFoundException;
import com.Assignment.Demo.exception.OrderNotFoundException;
import com.Assignment.Demo.exception.ProductNotFoundException;
import com.Assignment.Demo.models.Address;
import com.Assignment.Demo.models.Order;
import com.Assignment.Demo.models.dto.OrderRequestDTO;

public interface OrderService {

    public Order createOrder(OrderRequestDTO orderRequest) throws CustomerNotFoundException, ProductNotFoundException;

    public void cancelOrder(Long orderId) throws OrderNotFoundException;

    public void updateShippingAddress(Long orderId, Address address) throws OrderNotFoundException;


}
