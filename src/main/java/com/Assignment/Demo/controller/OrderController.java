package com.Assignment.Demo.controller;

import com.Assignment.Demo.exception.CustomerNotFoundException;
import com.Assignment.Demo.exception.OrderNotFoundException;
import com.Assignment.Demo.exception.ProductNotFoundException;
import com.Assignment.Demo.models.Address;
import com.Assignment.Demo.models.Order;
import com.Assignment.Demo.models.dto.OrderRequestDTO;
import com.Assignment.Demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //create order api
    @PostMapping("/create")
    public Order createOrder(@RequestBody OrderRequestDTO orderRequest) throws CustomerNotFoundException, ProductNotFoundException {

        Order createdOrder=orderService.createOrder(orderRequest);
        return createdOrder;


    }

    //cancellation

    @DeleteMapping("/cancel/{orderId}")
    public void cancelOrder(@PathVariable Long orderId) throws OrderNotFoundException {
        orderService.cancelOrder(orderId);
    }
    //updating shipping address of order

    @PutMapping("/updateshippingaddress/{orderId}")
    public void updateShippingAddress(@PathVariable Long orderId, @RequestBody Address address) throws OrderNotFoundException {
        orderService.updateShippingAddress(orderId,address);
    }

}
