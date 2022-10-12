package org.cleanbank.cleanbank.controller;

import org.cleanbank.cleanbank.entity.FixedDeposite;
import org.cleanbank.cleanbank.service.FixedDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Profile("test")
@RestController
public class FixedDepositController {
    @Autowired
    private FixedDepositService fixedDepositService;

    //Method to get All Fixed Deposits into Branch
    @RequestMapping("/branch/fd")
    @ResponseBody
    public List<FixedDeposite> getAllFDByBranch(@RequestParam("branchCode") int branchCode){
        return fixedDepositService.getAllFDByBranch(branchCode);
    }

    //Method To Get All FD By AccountNumber
    @RequestMapping("/branch/account/fd")
    @ResponseBody
    public List<FixedDeposite> getAllFDbyAccount(@RequestParam("accountNumber") int accountNumber){
        return fixedDepositService.getAllFDByAccount(accountNumber);
    }

    //Method To Get All FD Details
    @RequestMapping("/fixeddeposit")
    public List<FixedDeposite> getAllFDs(){
        return fixedDepositService.getAllFDs();
    }

    //Method To Add New FD into account
    @RequestMapping(method = RequestMethod.POST, value = "/branch/{branchCode}/account/{accountNumber}")
    public void addNewFDToAccount(@RequestBody FixedDeposite fixedDeposite, @PathVariable int accountNumber){
        fixedDepositService.addNewFDToAccount(fixedDeposite, accountNumber);
    }

    //Method To Delete FD From Account
    @RequestMapping(method = RequestMethod.DELETE, value = "/branch/{branchCode}/account/{accountNumber}/{fdNumber}")
    public void deleteFD(@PathVariable int fdNumber){
        fixedDepositService.deleteFD(fdNumber);
    }
}
