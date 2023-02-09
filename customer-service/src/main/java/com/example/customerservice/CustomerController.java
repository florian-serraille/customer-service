package com.example.customerservice;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private final CustomerRepository customerRepository;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    record Customer(Long id, String name) {
    }

    interface CustomerRepository {
        List<Customer> findAll();
    }

    @Component
    public static class CustomerRepositoryImpl implements CustomerRepository {

        @Override
        public List<Customer> findAll() {
            return null;
        }
    }
}