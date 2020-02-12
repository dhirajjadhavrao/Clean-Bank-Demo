package org.cleanbank.cleanbank.repository;

import org.cleanbank.cleanbank.entity.Account;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Profile("test")
public interface AccountRepository extends CrudRepository<Account, Integer> {
    public List<Account> findAllByBranchBranchCode(int branchCode);
    public Account findByAccountNumber(int accountNumber);
}
