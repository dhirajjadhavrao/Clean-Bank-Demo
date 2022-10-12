package org.cleanbank.cleanbank.controller;

import org.cleanbank.cleanbank.entity.Transaction;
import org.cleanbank.cleanbank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Profile("test")
@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    //To Get All Transactions By Branch
    @RequestMapping("/branch/transactions")
    @ResponseBody
    public List<Transaction> getAllTransactionByBranch(@RequestParam("branchCode") int branchCode){
        return  transactionService.getAllTransactionByBranch(branchCode);
    }

    //To Get All Transaction By Account
    @RequestMapping("/branch/account/transactions")
    @ResponseBody
    public List<Transaction> getAllTransactionByAccount(@RequestParam("accountNumber") int accountNumber){
        return transactionService.getAllTransactionByAccount(accountNumber);
    }

    //Method To Add New Transaction
    @RequestMapping(method = RequestMethod.POST, value = "/branch/{branchCode}/account/{accountNumber}/transaction")
    public void addNewTransaction(@RequestBody Transaction transaction, @PathVariable int accountNumber, @PathVariable int branchCode){
        transactionService.addNewTransaction(transaction, accountNumber, branchCode);
    }

    //Method To Delete Transaction
    @RequestMapping(method = RequestMethod.DELETE, value = "/branch/{branchCode}/account/{accountNumber}/transaction/{transactionId}")
    public void deleteTransaction(@PathVariable int transactionId){
        transactionService.deleteTransaction(transactionId);
    }
}
