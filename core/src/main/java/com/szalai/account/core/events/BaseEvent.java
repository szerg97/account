package com.szalai.account.core.events;

import com.szalai.account.core.messages.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEvent extends Message {

    private Integer version;

    public BaseEvent(String id) {
        super(id);
    }
}
