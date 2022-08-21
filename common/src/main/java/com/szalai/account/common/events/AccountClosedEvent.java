package com.szalai.account.common.events;

import com.szalai.account.core.events.BaseEvent;
import lombok.Data;

@Data
public class AccountClosedEvent extends BaseEvent {

    public AccountClosedEvent(String id) {
        super(id);
    }
}
