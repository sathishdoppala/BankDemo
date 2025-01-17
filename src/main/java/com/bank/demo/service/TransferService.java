package com.bank.demo.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.demo.model.Account;
import com.bank.demo.model.AccountTransfer;
import com.bank.demo.repo.TransferRepository;
import com.bank.demo.request.CreateTransfer;

@Service
public class TransferService {

	@Autowired
	private TransferRepository transferRepo;

	@Autowired
	private AccountService accountService;

	public AccountTransfer tranferAmout(CreateTransfer transfer) {
		Account fromAccount = accountService.getAccount(transfer.getFromAccountId());
		Account toAccount = accountService.getAccount(transfer.getToAccountId());
		
		
		if (null == fromAccount)
			
		{
			throw new RuntimeException("From account not found");
		}
		
		if (null == toAccount)
			
		{
			throw new RuntimeException("To account not found");
		}
			if( fromAccount.getBalance() <transfer.getAmount()) {
			throw new RuntimeException("Invalid Amount");
		}

		fromAccount.setBalance(fromAccount.getBalance() - transfer.getAmount());
		toAccount.setBalance(toAccount.getBalance() + transfer.getAmount());
		accountService.updateAccount(fromAccount);
		accountService.updateAccount(toAccount);
		AccountTransfer transferrepo = new  AccountTransfer();
		transferrepo.setFromAccountId(transfer.getFromAccountId());
		transferrepo.setToAccountId(transfer.getToAccountId());
		transferrepo.setAmount(transfer.getAmount());
		transferrepo.setTimestamp(new Date().toString());
		
		return transferRepo.save(transferrepo);
	}

	public List<AccountTransfer> getTransferHistory(String accountId) {
		return transferRepo.findAll().stream().filter(transfer -> transfer.getFromAccountId().equals(accountId)
				|| transfer.getToAccountId().equals(accountId)).collect(Collectors.toList());
	}

}
