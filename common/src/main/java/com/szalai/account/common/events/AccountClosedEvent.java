package com.szalai.account.common.events;

import com.szalai.account.core.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountClosedEvent extends BaseEvent {

    public AccountClosedEvent(String id) {
        super(id);
    }
}
