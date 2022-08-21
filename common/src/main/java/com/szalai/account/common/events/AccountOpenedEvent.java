package com.szalai.account.common.events;

import com.szalai.account.common.dto.AccountType;
import com.szalai.account.core.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountOpenedEvent extends BaseEvent {

    private String accountHolder;
    private AccountType accountType;
    private Date created;
    private Double openingBalance;

    public AccountOpenedEvent(String id, String accountHolder, AccountType accountType, Date created, Double openingBalance) {
        super(id);
        this.accountHolder = accountHolder;
        this.accountType = accountType;
        this.created = created;
        this.openingBalance = openingBalance;
    }
}
