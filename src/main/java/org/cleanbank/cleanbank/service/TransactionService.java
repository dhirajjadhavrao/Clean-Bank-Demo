package org.cleanbank.cleanbank.service;

import org.cleanbank.cleanbank.entity.Account;
import org.cleanbank.cleanbank.entity.Transaction;
import org.cleanbank.cleanbank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

//@Profile("test")
@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    //Method To Get All Transaction in Same branch
    public List<Transaction> getAllTransactionByBranch(int branchCode) {
        return transactionRepository.findAllByAccountBranchBranchCode(branchCode);
    }

    //Methhod To Get All Transaction for Account
    public List<Transaction> getAllTransactionByAccount(int accountNumber) {
        return transactionRepository.findAllByAccountAccountNumber(accountNumber);
    }

    //Method to Add New transaction for account
    public void addNewTransaction(Transaction transaction, int accountNumber, int branchCode){
        transaction.setAccountEntity(new Account(accountNumber,"",""));
        Transaction performTransaction = performTransaction(transaction,accountNumber, branchCode);
        transactionRepository.save(transaction);
    }

    public Transaction performTransaction(Transaction transaction, int accountNumber, int branchCode){
        Account transactionAccount = accountService.getSelectedAccountDetails(accountNumber);

        int transactionAmount = transaction.getAmount();

        if(transaction.getTransactionType().equalsIgnoreCase("debit")) {
            //Code For Debit
            if (transactionAccount.getAccountBalance() >= transactionAmount) {
                transactionAccount.setAccountBalance(transactionAccount.getAccountBalance() - transactionAmount);
                accountService.updateAccount(transactionAccount,branchCode);
            }
        }
        else
            {
                //Code For Credit
                transactionAccount.setAccountBalance(transactionAccount.getAccountBalance() + transactionAmount);
                accountService.updateAccount(transactionAccount, branchCode);

            }
        transaction.setAccountEntity(transactionAccount);
        return transaction;
    }
/*    //Method TO Perform Transaction
    public Transaction performTransaction(Transaction transactionEntity, int accountNumber, int branchCode){
        transactionEntity.setAccount(new Account(accountNumber,"",""));
        int accountBalanceCurrent = transactionEntity.getAccount().getAccountBalance();
        int transactionAmount = transactionEntity.getAmount();
        if(transactionEntity.getTransactionType().equalsIgnoreCase("debit")){
        if(accountBalanceCurrent >= transactionEntity.getAmount()){
            transactionEntity.getAccount().setAccountBalance(accountBalanceCurrent - transactionAmount);
        }
        }
        else{
            transactionEntity.getAccount().setAccountBalance(accountBalanceCurrent + transactionAmount);
        }
        accountService.updateAccount(transactionEntity.getAccount(), branchCode );

        return transactionEntity;
    }*/

    //Method to Delete Transaction
    public void deleteTransaction(int transactionId) {
        transactionRepository.deleteById(transactionId);
    }

    //Method To Get All Transactions
    public List<Transaction> getAllTransactions() {
        return (List<Transaction>) transactionRepository.findAll();
    }
}
