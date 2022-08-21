package com.szalai.account.common.events;

import com.szalai.account.core.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FundsDepositedEvent extends BaseEvent {

    private Double amount;

    public FundsDepositedEvent(String id, Double amount) {
        super(id);
        this.amount = amount;
    }
}
