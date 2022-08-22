package com.szalai.account.query.domain;

import com.szalai.account.core.domain.BaseEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<BankAccount, String> {
    Optional<BankAccount> findByAccountHolder(String accountHolder);
    List<BaseEntity> findByBalanceGreaterThan(Double balance);
    List<BaseEntity> findByBalanceLessThan(Double balance);
}
