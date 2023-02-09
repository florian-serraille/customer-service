package com.example.customerclient;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

@SpringBootTest
@AutoConfigureStubRunner(ids = "com.example:customer-service:+:8081", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class CustomerClientTest {

    @Autowired
    private CustomerClient customerClient;

    @Test
    void shouldReturnAllCustomers(){

        Assertions.assertThat(customerClient.requestAll())
                .containsExactly(
                        new CustomerClient.Customer(1L, "Lyvia"),
                        new CustomerClient.Customer(2L, "Rebeca"),
                        new CustomerClient.Customer(3L, "Alexandre"),
                        new CustomerClient.Customer(4L, "Erivan"),
                        new CustomerClient.Customer(5L, "Lucas")
                );
    }
}
