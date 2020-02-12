package org.cleanbank.cleanbank.repository;

import org.cleanbank.cleanbank.entity.Branch;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

@Profile("test")
public interface BranchRepository extends CrudRepository<Branch, Integer> {
    public Branch findByBranchCode(int branchCode);
}
