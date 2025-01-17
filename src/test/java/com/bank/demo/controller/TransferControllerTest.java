package com.bank.demo.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.bank.demo.model.AccountTransfer;
import com.bank.demo.repo.AccountRepo;
import com.bank.demo.request.CreateTransfer;
import com.bank.demo.service.TransferService;

@SpringBootTest
@AutoConfigureMockMvc
class TransferControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @Mock
    private TransferService transferService;

    @Mock
    private AccountRepo accountRepo;
    private CreateTransfer validRequest;

    @BeforeEach
    public void setUp() {
        validRequest = new CreateTransfer();
        validRequest.setFromAccountId("907b8f1b-590b-4eed-a975-005bc087520b");
        validRequest.setToAccountId("7aed747b-1ffd-4898-9d8a-59804f41967d");
        validRequest.setAmount(10);
    }

    
    @Test
    public void testTransferAmount_Success() throws Exception {
        // Mock the service to return true (indicating successful transfer)
    	AccountTransfer tranferRquest= new AccountTransfer();
    	tranferRquest.setFromAccountId("907b8f1b-590b-4eed-a975-005bc087520b");
    	tranferRquest.setToAccountId("7aed747b-1ffd-4898-9d8a-59804f41967d");
    	tranferRquest.setAmount(10);
        when(transferService.tranferAmout(validRequest)).thenReturn(tranferRquest);

        mockMvc.perform(post("/transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"fromAccountId\":907b8f1b-590b-4eed-a975-005bc087520b, \"toAccountId\":7aed747b-1ffd-4898-9d8a-59804f41967d, \"amount\":10.00}"))
                .andExpect(status().isOk());
               
    }
    
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
