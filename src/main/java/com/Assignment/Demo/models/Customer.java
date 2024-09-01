package com.Assignment.Demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {
        @Id
        @GeneratedValue(
                strategy = GenerationType.IDENTITY
        )
        private Long customerId;
        private String firstName;
        private String lastName;

        @Column(nullable = false)
        private String email;

        @Column(length = 12)
        private String phoneNumber;


}
