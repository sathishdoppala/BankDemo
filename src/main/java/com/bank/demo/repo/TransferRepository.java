package com.bank.demo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bank.demo.model.AccountTransfer;

public interface TransferRepository extends MongoRepository<AccountTransfer, String> {

}
