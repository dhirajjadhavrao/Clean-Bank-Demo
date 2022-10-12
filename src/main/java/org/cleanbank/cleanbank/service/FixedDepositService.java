package org.cleanbank.cleanbank.service;

import org.cleanbank.cleanbank.entity.Account;
import org.cleanbank.cleanbank.entity.FixedDeposite;
import org.cleanbank.cleanbank.repository.FixedDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

//@Profile("test")
@Service
public class FixedDepositService {
    @Autowired
    private FixedDepositRepository fixedDepositRepository;

    //Method To Get All FD By Branch
    public List<FixedDeposite> getAllFDByBranch(int branchCode) {
        return fixedDepositRepository.findAllByAccountBranch(branchCode);
    }

    //Method To Get All FD By Account
    public List<FixedDeposite> getAllFDByAccount(int accountNumber){
        return fixedDepositRepository.findAllByAccountAccountNumber(accountNumber);
    }

    //Method TO Add New FD to Related Account
    public void addNewFDToAccount(FixedDeposite fixedDeposite, int accountNumber) {
        fixedDeposite.setAccount(new Account(accountNumber,"",""));
        fixedDepositRepository.save(fixedDeposite);
    }

    //Method To Delete FD From Account
    public void deleteFD(int fdNumber) {
        fixedDepositRepository.deleteById(fdNumber);
    }

    //Method To Get All FDs
    public List<FixedDeposite> getAllFDs() {
        return (List<FixedDeposite>) fixedDepositRepository.findAll();
    }
}
