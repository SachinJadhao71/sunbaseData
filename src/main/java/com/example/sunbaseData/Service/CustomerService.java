package com.example.sunbaseData.Service;

import com.example.sunbaseData.DTO.CustomerRequestDto;
import com.example.sunbaseData.DTO.CustomerResponseDto;
import com.example.sunbaseData.Model.Customer;
import com.example.sunbaseData.Repository.CustomerRepository;
import com.example.sunbaseData.Transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    public CustomerResponseDto addCustomer(Customer customer) {

       Customer savedCustomer = customerRepository.save(customer);

//       prepare responsedto

        CustomerResponseDto customerResponseDto = CustomerTransformer.customerToCustomerResponseDto(savedCustomer);

        return customerResponseDto;
    }

    public List<CustomerResponseDto> getCustomerList() {

        List<Customer> customers = customerRepository.findAll();

        List<CustomerResponseDto> responseDtos = new ArrayList<>();

        for(Customer customer : customers){
            responseDtos.add(CustomerTransformer.customerToCustomerResponseDto(customer));
        }

        return responseDtos;
    }

    public String deleteCustomer(int id) {

        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if(optionalCustomer.isEmpty()){
            throw new RuntimeException("UUID not found");
        }

        Customer customer = optionalCustomer.get();

        customerRepository.deleteById(id);

        return "Successfully deleted";
    }

    public CustomerResponseDto updateCustomer(int id) {

        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if(optionalCustomer.isEmpty()){
            throw new RuntimeException("UUID not found");
        }


        Customer customer = optionalCustomer.get();

        Customer savedCustomer = customerRepository.save(customer);

//        prepare response dto

        return CustomerTransformer.customerToCustomerResponseDto(savedCustomer);
    }
}
