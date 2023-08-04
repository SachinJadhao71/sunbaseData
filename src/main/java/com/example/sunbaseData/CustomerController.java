package com.example.sunbaseData;

import com.example.sunbaseData.DTO.CustomerResponseDto;
import com.example.sunbaseData.Model.Customer;
import com.example.sunbaseData.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity addCustomer(@RequestBody Customer customer){
        CustomerResponseDto responseDto = customerService.addCustomer(customer);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/get_customer_list")
    public ResponseEntity getCustomerList(){
        List<CustomerResponseDto> responseDtos = customerService.getCustomerList();
        return new ResponseEntity<>(responseDtos,HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity deleteCustomer(@RequestParam("uuid") int id){
        try {
            String ans = customerService.deleteCustomer(id);
            return new ResponseEntity(ans,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update")
    public ResponseEntity updateCustomer(@RequestParam("uuid") int id){
        try {
            CustomerResponseDto responseDto = customerService.updateCustomer(id);
            return new ResponseEntity<>(responseDto,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
