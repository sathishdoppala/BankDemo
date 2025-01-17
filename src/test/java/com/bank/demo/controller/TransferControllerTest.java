package com.bank.demo.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

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
    public void testTransferAmount_BadRequest() throws Exception {
        // Mock the service to return true (indicating successful transfer)
    	AccountTransfer tranferRquest= new AccountTransfer();
    	tranferRquest.setFromAccountId("907b8f1b-590b-4eed-a975-005bc087520b");
    	tranferRquest.setToAccountId("7aed747b-1ffd-4898-9d8a-59804f41967d");
    	tranferRquest.setAmount(10);
        when(transferService.tranferAmout(validRequest)).thenThrow(new IllegalArgumentException());

        mockMvc.perform(post("/transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"fromAccountId\":, \"toAccountId\":7aed747b-1ffd-4898-9d8a-59804f41967d, \"amount\":10.00}"))
                .andExpect(status().isBadRequest());
               
    }
    
    
    @Test
    public void testGetTransferHistory_Success() throws Exception {
        // Arrange
    	 String accountId = "7aed747b-1ffd-4898-9d8a-59804f41967d";
    	 
    		AccountTransfer tranferRquest= new AccountTransfer();
        	tranferRquest.setFromAccountId("907b8f1b-590b-4eed-a975-005bc087520b");
        	tranferRquest.setToAccountId("7aed747b-1ffd-4898-9d8a-59804f41967d");
        	tranferRquest.setAmount(10);
        	
        	AccountTransfer tranferRquest2= new AccountTransfer();
        	tranferRquest2.setFromAccountId("907b8f1b-590b-4eed-a975-005bc087520b");
        	tranferRquest2.setToAccountId("7aed747b-1ffd-4898-9d8a-59804f41967d");
        	tranferRquest2.setAmount(10);
        	
        	
        List<AccountTransfer> transferHistoryList = Arrays.asList(tranferRquest, tranferRquest2);
        when(transferService.getTransferHistory(accountId)).thenReturn(transferHistoryList);

        // Act and Assert
        mockMvc.perform(get("/transactions/{accountId}", accountId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
              
    }

    @Test
    public void testGetTransferHistory_NoTransfers() throws Exception {
        // Arrange
        String accountId = "7aed747b-1ffd-4898-9d8a-59804f41967d"; // Account with no transfers
        List<AccountTransfer> emptyTransferHistory = Arrays.asList();
        when(transferService.getTransferHistory(accountId)).thenReturn(emptyTransferHistory);

        // Act and Assert
        mockMvc.perform(get("/transactions/{accountId}", accountId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    public void testGetTransferHistory_NotFound() throws Exception {
        // Arrange
        String accountId =  "7aed747b-1ffd-4898-9d8a-59804f41967d1"; // Invalid account ID
        when(transferService.getTransferHistory(accountId)).thenReturn(null);

        // Act and Assert
        mockMvc.perform(get("/transactions/{accountId}", accountId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    
    
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
