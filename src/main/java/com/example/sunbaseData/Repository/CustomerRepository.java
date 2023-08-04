package com.example.sunbaseData.Repository;

import com.example.sunbaseData.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
