package com.szalai.account.query.infrastructure.handlers;

import com.szalai.account.common.events.AccountClosedEvent;
import com.szalai.account.common.events.AccountOpenedEvent;
import com.szalai.account.common.events.FundsDepositedEvent;
import com.szalai.account.common.events.FundsWithdrawnEvent;
import com.szalai.account.query.domain.AccountRepository;
import com.szalai.account.query.domain.BankAccount;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountEventHandler implements EventHandler{

    private final AccountRepository accountRepository;

    @Override
    public void on(AccountOpenedEvent event) {
        BankAccount account = BankAccount
                .builder()
                .id(event.getId())
                .accountHolder(event.getAccountHolder())
                .creationDate(event.getCreated())
                .accountType(event.getAccountType())
                .balance(event.getOpeningBalance())
                .build();
        accountRepository.save(account);
    }

    @Override
    public void on(FundsDepositedEvent event) {
        Optional<BankAccount> account = accountRepository.findById(event.getId());
        if(account.isEmpty()){
            return;
        }

        Double currentBalance = account.get().getBalance();
        Double latestBalance = currentBalance + event.getAmount();
        account.get().setBalance(latestBalance);
        accountRepository.save(account.get());
    }

    @Override
    public void on(FundsWithdrawnEvent event) {
        Optional<BankAccount> account = accountRepository.findById(event.getId());
        if(account.isEmpty()){
            return;
        }

        Double currentBalance = account.get().getBalance();
        Double latestBalance = currentBalance - event.getAmount();
        account.get().setBalance(latestBalance);
        accountRepository.save(account.get());
    }

    @Override
    public void on(AccountClosedEvent event) {
        Optional<BankAccount> account = accountRepository.findById(event.getId());
        if(account.isEmpty()){
            return;
        }

        accountRepository.deleteById(event.getId());
    }
}
