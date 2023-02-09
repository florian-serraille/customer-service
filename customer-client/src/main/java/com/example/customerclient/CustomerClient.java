package com.example.customerclient;

import org.springframework.asm.TypeReference;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

public class CustomerClient {

    private final RestTemplate restTemplate;

    public CustomerClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Customer> requestAll() {
        final var reference = new ParameterizedTypeReference<List<Customer>>() {};
        final var response = restTemplate.exchange("http://localhost:8081/customers", HttpMethod.GET, null, reference);
        return response.getBody();
    }

    record Customer(Long id, String name){}
}
