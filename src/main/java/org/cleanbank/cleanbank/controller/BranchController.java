package org.cleanbank.cleanbank.controller;

import org.cleanbank.cleanbank.entity.Branch;
import org.cleanbank.cleanbank.entity.Response;
import org.cleanbank.cleanbank.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Profile("test")
@RestController
public class BranchController {
    @Autowired
    private BranchService branchService;

    //Method To Set Welcome Message
    @RequestMapping("/")
    public String welcomeMessage(){
        return "Welcome in Bank"+"\nFor Branch Details /branch" +
                "\nFor All Accounts /branch/account"+
                "\nFor All Fixed Deposits /branch/fixeddeposit"+
                "\nFor transaction /branch/transactions";
    }

    //Method TO Add New Branch into Database
    @RequestMapping(method = RequestMethod.POST, value = "/branch")
    public Response addNewBranch(@RequestBody Branch branch){
        return branchService.addNewBranch(branch);
    }

    //Method To edit Branch Details
    @RequestMapping(method = RequestMethod.PUT, value = "/branch")
    public ResponseEntity<?> updateBranchDetails(@RequestBody Branch branch){
        return branchService.updateBranchDetails(branch);
    }

    //Method To Get All Branch Details
    @RequestMapping("/branch")
    public List<Branch> getAllBranchDetails(){
        return branchService.getAllBranchDetails();
    }

    //Method To Get Branch Details By BranchCode
    @RequestMapping("/branch/details")
    @ResponseBody
    public Branch getSelectedBranch(@RequestParam("branchCode") int branchCode){
        return branchService.getSelectedBranchDetails(branchCode);
    }

    //Method To Delete Selected Branch
    @RequestMapping(method = RequestMethod.DELETE, value = "/branch")
    @ResponseBody
    public Response deleteSelectedBranch(@RequestParam("branchCode") int branchCode){
       return branchService.deleteSelectedBranch(branchCode);
    }
}
