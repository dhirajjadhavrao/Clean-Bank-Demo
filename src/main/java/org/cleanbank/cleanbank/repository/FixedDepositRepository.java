package org.cleanbank.cleanbank.repository;

import org.cleanbank.cleanbank.entity.FixedDeposite;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//@Profile("test")
public interface FixedDepositRepository extends CrudRepository<FixedDeposite, Integer> {
   public   List<FixedDeposite> findAllByAccountBranch(int branchCode);

   public List<FixedDeposite> findAllByAccountAccountNumber(int accountNumber);
}
