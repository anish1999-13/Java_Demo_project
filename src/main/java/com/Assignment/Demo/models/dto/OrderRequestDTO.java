package com.Assignment.Demo.models.dto;

import com.Assignment.Demo.models.Address;
import com.Assignment.Demo.models.Payment;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequestDTO {

    private Address address;

    private Long customerId;

    private Payment payment;

    private List<OrderItemDTO> items;


}
