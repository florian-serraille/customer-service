package com.example.customerservice;


import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest
public class ContractBaseClass {

    @Autowired
    private CustomerController customerController;

    @MockBean
    private CustomerController.CustomerRepository customerRepository;

    @BeforeEach
    void setUp(){

        Mockito.when(customerRepository.findAll())
                .thenReturn(List.of(
                        new CustomerController.Customer(1L, "Lyvia"),
                        new CustomerController.Customer(2L, "Rebeca"),
                        new CustomerController.Customer(3L, "Alexandre"),
                        new CustomerController.Customer(4L, "Erivan"),
                        new CustomerController.Customer(5L, "Lucas")
                ));

        RestAssuredMockMvc.standaloneSetup(this.customerController);
    }

}
