package com.bank.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.demo.request.CreateAccountRequest;
import com.bank.demo.response.CreateAccountResponse;
import com.bank.demo.service.AccountService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AccountController {

	@Autowired
	private AccountService accountService;

	@PostMapping(value = "/createAccount")
	public ResponseEntity<?> createAccount(@Valid @RequestBody CreateAccountRequest request) {
		log.info("AccountController entered:createAccount");
		try {
			
		CreateAccountResponse bankAccount = accountService.createAccount(request);
		return new ResponseEntity<>(bankAccount, HttpStatus.CREATED);
		}catch(IllegalArgumentException ie) 
		{
			return new ResponseEntity<>(ie.getMessage(),HttpStatus.BAD_REQUEST);
		}
		catch(Exception e)
		{
			log.error("AccountController ::error occured"+e.getMessage());
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
	}
		

}
