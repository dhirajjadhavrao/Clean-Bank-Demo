package org.cleanbank.cleanbank.service;

import org.cleanbank.cleanbank.entity.Account;
import org.cleanbank.cleanbank.entity.Branch;
import org.cleanbank.cleanbank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

//@Profile("test")
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    //Method TO Get Account Details According branches
    public List<Account> getAllAccountsInBranch(int branchCode) {
        return accountRepository.findAllByBranchBranchCode(branchCode);
    }

    //Method To Add New Account into branch
    public void addNewAccount(Account account, int branchCode) {
        account.setBranch(new Branch(branchCode,"",""));
        accountRepository.save(account);
    }

    //Method To Delete Account from Database
    public void deleteAccountFromBranch(int accountNumber){
        accountRepository.deleteById(accountNumber);
    }

    //Method TO get Selected AccountDetails
    public Account getSelectedAccountDetails(int accountNumber){
        return  accountRepository.findByAccountNumber(accountNumber);
    }

    //Method To EditAccount from DataBase
    public void updateAccount(Account account, int branchCode) {
        account.setBranch(new Branch(branchCode,"",""));
        accountRepository.save(account);
    }

    //Method TO Get All Accounts Into TO All Branches
    public List<Account> getAllAccounts() {
       return (List<Account>) accountRepository.findAll();
    }
}
