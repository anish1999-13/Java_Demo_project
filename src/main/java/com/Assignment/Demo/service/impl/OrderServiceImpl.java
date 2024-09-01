package com.Assignment.Demo.service.impl;

import com.Assignment.Demo.exception.CustomerNotFoundException;
import com.Assignment.Demo.exception.OrderNotFoundException;
import com.Assignment.Demo.exception.ProductNotFoundException;
import com.Assignment.Demo.models.*;
import com.Assignment.Demo.models.dto.OrderItemDTO;
import com.Assignment.Demo.models.dto.OrderRequestDTO;
import com.Assignment.Demo.repository.CustomerRepository;
import com.Assignment.Demo.repository.OrderRepository;
import com.Assignment.Demo.repository.ProductRepository;
import com.Assignment.Demo.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;
    @Override
    public Order createOrder(OrderRequestDTO orderRequest) throws CustomerNotFoundException, ProductNotFoundException {

        Long customerId= orderRequest.getCustomerId();
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if(customerOptional.isEmpty())
        {
            throw new CustomerNotFoundException("No Customer found with Id"+customerId);
        }


        Order order = new Order();
        Customer customer = customerOptional.get();

        order.setCustomer(customer);
        order.setShippingAddress(orderRequest.getAddress());
        order.setStatus(Status.CREATED);
        order.setCreateAt(LocalDateTime.now());
        Payment processedPayment=processPayment(orderRequest.getPayment());
        order.setPayment(processedPayment);

        Set<OrderItems> items= new HashSet<>();

        double subtotal = 0;
        double tax = 0;
        double shippingCharges = 0;
        double totalPrice;


        for(OrderItemDTO item: orderRequest.getItems()){
            Optional<Product> productOptional=productRepository.findById(item.getProductId());
            if(productOptional.isEmpty()){
                throw new ProductNotFoundException("No Product found");
            }
            Product product = productOptional.get();
            double unitPrice = product.getUnitPrice();
            int quantity = item.getRequestedQuantity();


            OrderItems i = new OrderItems();
            i.setProduct(product);
            i.setOrder(order);
            i.setOrderedQuantity(item.getRequestedQuantity());
            items.add(i);

            subtotal += product.getUnitPrice() * item.getRequestedQuantity();
        }
        tax = subtotal * 0.005;
        if (subtotal < 35) {
            shippingCharges = 10.0;
        } else {
            shippingCharges = 0.0;
        }

        // Calculate total price
        totalPrice = subtotal + tax + shippingCharges;


        order.setSubTotal(subtotal);
        order.setTax(tax);
        order.setShippingCharges(shippingCharges);
        order.setTotalPrice(totalPrice);
        order.setItems(items);
        Order createdOrder=orderRepository.save(order);

        return createdOrder;

    }

    private Payment processPayment(Payment payment){
        // assume calling third party api and getting confirmation code

        payment.setConfirmationCode("XYZ123");
        return payment;
    }
    @Override
    public void cancelOrder(Long orderId) throws OrderNotFoundException {
        Optional<Order> optionalOrder= orderRepository.findById(orderId);
        if(optionalOrder.isEmpty()){
            throw new OrderNotFoundException("Order not Found");
        }
        Order order= optionalOrder.get();
        order.setStatus(Status.CANCELLED);
        orderRepository.save(order);
    }

    @Override
    public void updateShippingAddress(Long orderId, Address address) throws OrderNotFoundException {
        Optional<Order> optionalOrder= orderRepository.findById(orderId);
        if(optionalOrder.isEmpty()){
            throw new OrderNotFoundException("Order not found");
        }
        Order order=optionalOrder.get();
        order.setShippingAddress(address);
        orderRepository.save(order);
    }
}
