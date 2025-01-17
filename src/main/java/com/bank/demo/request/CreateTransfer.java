package com.bank.demo.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class CreateTransfer {

	private String fromAccountId;
	private String toAccountId;
	private double amount;
}
