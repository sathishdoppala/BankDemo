package com.bank.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.demo.model.AccountTransfer;
import com.bank.demo.request.CreateTransfer;
import com.bank.demo.service.TransferService;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class TransferController {
	
@Autowired
private TransferService transferService;

@PostMapping("/transfer")
public ResponseEntity<?> transferAmount(@RequestBody CreateTransfer createTransfer )
{
	log.info("TransferController entered::transferAmount");
	try {
		AccountTransfer accountTransfer=transferService.tranferAmout(createTransfer);
		log.info("TransferController ended::transferAmount");
		return new ResponseEntity<>(accountTransfer,HttpStatus.CREATED);
	}catch(IllegalArgumentException ie) 
	{
		return new ResponseEntity<>(ie.getMessage(),HttpStatus.BAD_REQUEST);
	}
	catch(Exception e)
	{
		log.error("TransferController ::error occured"+e.getMessage());
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
}

@GetMapping("/transactions/{accountId}")
public ResponseEntity<List<AccountTransfer>> getTransactionHistory(@PathVariable String accountId){
	List<AccountTransfer> transfers = transferService.getTransferHistory(accountId);
	return new ResponseEntity<>(transfers,HttpStatus.OK);
}

}
