package com.szalai.account.query.infrastructure.handlers;

import com.szalai.account.common.events.AccountClosedEvent;
import com.szalai.account.common.events.AccountOpenedEvent;
import com.szalai.account.common.events.FundsDepositedEvent;
import com.szalai.account.common.events.FundsWithdrawnEvent;

public interface EventHandler {
    void on(AccountOpenedEvent event);
    void on(FundsDepositedEvent event);
    void on(FundsWithdrawnEvent event);
    void on(AccountClosedEvent event);
}
