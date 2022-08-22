package com.szalai.account.core.handlers;

import com.szalai.account.core.domain.AggregateRoot;

public interface EventSourcingHandler <T>{
    void save(AggregateRoot aggregate);
    T getById(String id); //returns the latest state of the aggregate
}
