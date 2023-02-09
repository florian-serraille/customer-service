package com.example.customerclient;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerClientConfiguration {

    @Bean
    public CustomerClient customerClient(){
        return new CustomerClient(new RestTemplateBuilder().build());
    }
}
