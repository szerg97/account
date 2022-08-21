package com.szalai.account.core.infrastructure;

import com.szalai.account.core.events.BaseEvent;

import java.util.List;

public interface EventStore {

    void saveEvents(String aggregateId, Iterable<BaseEvent> events, Integer expectedVersion);
    List<BaseEvent> getEvents(String aggregateId);
}
