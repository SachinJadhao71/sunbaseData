package com.example.sunbaseData.Transformer;

import com.example.sunbaseData.DTO.CustomerResponseDto;
import com.example.sunbaseData.Model.Customer;

public class CustomerTransformer {

    public static CustomerResponseDto customerToCustomerResponseDto(Customer customer){

       return CustomerResponseDto.builder()
               .email(customer.getEmail())
               .state(customer.getState())
               .phone(customer.getPhone())
               .city(customer.getCity())
               .street(customer.getStreet())
               .first_name(customer.getFirst_name())
               .last_name(customer.getLast_name())
               .address(customer.getAddress())
               .build();
    }
}
