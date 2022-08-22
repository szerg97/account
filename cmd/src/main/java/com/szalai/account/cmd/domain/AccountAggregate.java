package com.szalai.account.cmd.domain;

import com.szalai.account.cmd.api.commands.OpenAccountCommand;
import com.szalai.account.common.events.AccountClosedEvent;
import com.szalai.account.common.events.AccountOpenedEvent;
import com.szalai.account.common.events.FundsDepositedEvent;
import com.szalai.account.common.events.FundsWithdrawnEvent;
import com.szalai.account.core.domain.AggregateRoot;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
public class AccountAggregate extends AggregateRoot {

    private Boolean active;
    private Double balance;

    public AccountAggregate(OpenAccountCommand command) {
        raiseEvent(new AccountOpenedEvent(
                command.getId(),
                command.getAccountHolder(),
                command.getAccountType(),
                new Date(),
                command.getOpeningBalance()
        ));
    }

    public Double getBalance() {
        return balance;
    }

    public void apply(AccountOpenedEvent event){
        this.id = event.getId();
        this.active = true;
        this.balance = event.getOpeningBalance();
    }

    public void depositFunds(Double amount){
        if (!this.active){
            throw new IllegalStateException("Funds cannot be deposited into a closed bank account");
        }
        if (amount <= 0){
            throw new IllegalStateException("The deposited amount must be greater than zero");
        }

        raiseEvent(new FundsDepositedEvent(
                this.id,
                amount
        ));
    }

    public void apply(FundsDepositedEvent event){
        this.id = event.getId();
        this.balance += event.getAmount();
    }

    public void withdrawFunds(Double amount){
        if (!this.active){
            throw new IllegalStateException("Funds cannot be withdrawn from a closed bank account");
        }

        raiseEvent(new FundsWithdrawnEvent(
                this.id,
                amount
        ));
    }

    public void apply(FundsWithdrawnEvent event){
        this.id = event.getId();
        this.balance -= event.getAmount();
    }

    public void closeAccount(){
        if (!this.active){
            throw new IllegalStateException("The bank account has already been closed");
        }

        raiseEvent(new AccountClosedEvent(
                this.id
        ));
    }

    public void apply(AccountClosedEvent event){
        this.id = event.getId();
        this.active = false;
    }
}
