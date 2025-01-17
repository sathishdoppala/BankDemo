package com.bank.demo.controller;
 
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bank.demo.response.CustomerResponse;
import com.bank.demo.service.CustomerService;
 
@SpringBootTest
public class CustomerControllerTest {
 
    @Autowired
    private MockMvc mockMvc;
 
    @Mock
    private CustomerService customerService;
 
    @InjectMocks
    private CustomerController customerController;
 
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }
 
    @Test
    public void testGetCustomerByDobAndSsn() throws Exception {
        String dob = "1990-01-01";
        String ssn = "123-45-6789";
        CustomerResponse customerResponse = new CustomerResponse();
        // ...set properties on customerResponse...
 
        when(customerService.findCustomerByDobAndSsn(dob, ssn)).thenReturn(customerResponse);
 
        mockMvc.perform(get("/customer")
                .param("dob", dob)
                .param("ssn", ssn))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.property").value("expectedValue")); // replace with actual property and value
    }
}