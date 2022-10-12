package org.cleanbank.cleanbank.repository;

import org.cleanbank.cleanbank.entity.Transaction;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//@Profile("test")
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
   public List<Transaction> findAllByAccountBranchBranchCode(int branchCode);

   public  List<Transaction> findAllByAccountAccountNumber(int accountNumber);
}
