package com.bank.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data

@Document(collection ="transfer")
public class AccountTransfer {
	@Id
	String id;
	private String fromAccountId;
    private String toAccountId;
    private double amount;
    private String timestamp;

}
