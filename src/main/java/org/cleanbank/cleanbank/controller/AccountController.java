package org.cleanbank.cleanbank.controller;

import org.cleanbank.cleanbank.entity.Account;
import org.cleanbank.cleanbank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Profile("test")
@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    //Method To Get All Accounts Present In All Branches
    @RequestMapping("/accounts")
    public List<Account> getAllAccounts(){
        return accountService.getAllAccounts();
    }

    //Method To Get All Accounts Into Branch
    @RequestMapping("/branch/accounts")
    @ResponseBody
    public List<Account> getAllAccountsInBranch(@RequestParam("branchCode") int branchCode){
        return accountService.getAllAccountsInBranch(branchCode);
    }

    //Method To Add Account into Branch
    @RequestMapping(method = RequestMethod.POST, value = "/branch/{branchCode}/accounts")
    public void addNewAccount(@RequestBody Account account, @PathVariable int branchCode){
        accountService.addNewAccount(account, branchCode);
    }

    //Method TO Edit Account Details
    @RequestMapping(method = RequestMethod.PUT, value = "/branch/{branchCode}/accounts")
    public void editAccount(@RequestBody Account account, @PathVariable int branchCode){
        accountService.updateAccount(account, branchCode);
    }

    //Method To Delete Account form Branch
    @RequestMapping(method = RequestMethod.DELETE, value = "/branch/{branchCode}/accounts/{accountNumber}")
    public void deleteAccountFromBranch(@PathVariable int accountNumber){
        accountService.deleteAccountFromBranch(accountNumber);
    }
}
