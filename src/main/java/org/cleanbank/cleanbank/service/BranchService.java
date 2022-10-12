package org.cleanbank.cleanbank.service;

import org.cleanbank.cleanbank.entity.Branch;
import org.cleanbank.cleanbank.entity.Response;
import org.cleanbank.cleanbank.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

//@Profile("test")
@Service
public class BranchService {
    @Autowired
    BranchRepository branchRepository;

    //Constructor
    public BranchService() {
    }

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    //Method To Add New Branch
    public Response addNewBranch(Branch branch) {
        branchRepository.save(branch);
        return new Response(branch.getBranchCode()+" is Added Successfully",Boolean.TRUE);
    }

    //Method To Get All Branch Details
    public List<Branch> getAllBranchDetails() {
       return (List<Branch>) branchRepository.findAll();
    }

    //Method TO Get Details For Selected Branch
    public Branch getSelectedBranchDetails(int branchCode) {
        return branchRepository.findByBranchCode(branchCode);
    }

    //Method To Delete branch
    public Response deleteSelectedBranch(int branchCode) {
        branchRepository.deleteById(branchCode);
        return new Response(branchCode+" is Deleted",Boolean.TRUE);
    }

    //Method TO EDIT Branch Details
    public ResponseEntity<?> updateBranchDetails(Branch branch) {
        branchRepository.save(branch);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
