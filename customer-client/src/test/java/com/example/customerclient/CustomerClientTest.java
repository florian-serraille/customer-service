package com.example.customerclient;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@SpringBootTest
@AutoConfigureWireMock(port = 8081)
public class CustomerClientTest {

    @Autowired
    private CustomerClient customerClient;

    @Test
    void shouldReturnAllCustomers(){

        setUpMockServer();

        Assertions.assertThat(customerClient.requestAll())
                .containsExactly(
                        new CustomerClient.Customer(1L, "Lyvia"),
                        new CustomerClient.Customer(2L, "Rebeca"),
                        new CustomerClient.Customer(3L, "Alexandre"),
                        new CustomerClient.Customer(4L, "Erivan"),
                        new CustomerClient.Customer(5L, "Lucas")
                );
    }

    private void setUpMockServer(){

        final var response = """
                [
                    {"id": 1, "name": "Lyvia"},
                    {"id": 2, "name": "Rebeca"},
                    {"id": 3, "name": "Alexandre"},
                    {"id": 4, "name": "Erivan"},
                    {"id": 5, "name": "Lucas"}
                ]
                """;


        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo("/customers"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(response)));
    }
}
