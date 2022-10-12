package com.springboot.docker.seed;

import com.springboot.docker.entity.Account;
import com.springboot.docker.entity.Branch;
import com.springboot.docker.repository.AccountRepository;
import com.springboot.docker.repository.BranchRepository;
import com.springboot.docker.repository.FixedDepositRepository;
import com.springboot.docker.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseSeeder {
    private Logger logger = Logger.getLogger(DatabaseSeeder.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private FixedDepositRepository fixedDepositRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event){
        seedData();
    }

    private void seedData(){
        Branch mumbai = new Branch(1, "Mumbai","Mumbai");
        Branch pune = new Branch(2,"Pune","Pune");

        Account accountOne = new Account(1111,"TestMumbaiOne","Mumbai", 10000, mumbai.getBranchCode());
        Account accountTwo = new Account(1112,"TestMumbaiTwo","Mumbai", 30000, mumbai.getBranchCode());
        Account accountThree = new Account(2111,"TestPuneOne","Pune", 20000, pune.getBranchCode());
        Account accountFour = new Account(2112,"TestPuneTwo","Pune", 25000, pune.getBranchCode());

        List<Branch> branches = (List<Branch>) branchRepository.findAll();
        if(branches == null || branches.size() <= 0){
            branchRepository.saveAll(Arrays.asList(mumbai, pune));
            logger.info("Branch table is seeded !");
        } else {
            logger.info("Branch Seeding Not Required !");
        }

        List<Account> accounts = (List<Account>) accountRepository.findAll();
        if(accounts == null || accounts.size() <= 0){
            accountRepository.saveAll(Arrays.asList(accountOne, accountTwo, accountThree, accountFour));
            logger.info("Account table is seeded !");
        } else {
            logger.info("Account Seeding Not Required !");
        }
    }
}
