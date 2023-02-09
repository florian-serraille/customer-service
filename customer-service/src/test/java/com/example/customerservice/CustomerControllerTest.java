package com.example.customerservice;

import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@WebMvcTest
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerController.CustomerRepository customerRepository;

    @Test
    void setupTest() {
        Assertions.assertThat(mockMvc).isNotNull();
    }

    @Test
    void shouldReturnAllCustomers() throws Exception {

        Mockito.when(customerRepository.findAll())
                .thenReturn(List.of(
                        new CustomerController.Customer(1L, "Lyvia"),
                        new CustomerController.Customer(2L, "Rebeca"),
                        new CustomerController.Customer(3L, "Alexandre"),
                        new CustomerController.Customer(4L, "Erivan"),
                        new CustomerController.Customer(5L, "Lucas")
                ));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/customers")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("@.*").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("@.*", Matchers.hasSize(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[0].name").value("Lyvia"))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[1].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[1].name").value("Rebeca"))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[2].id").value(3L))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[2].name").value("Alexandre"))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[3].id").value(4L))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[3].name").value("Erivan"))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[4].id").value(5L))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[4].name").value("Lucas"));

    }
}