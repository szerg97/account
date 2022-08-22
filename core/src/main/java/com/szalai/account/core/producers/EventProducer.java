package com.szalai.account.core.producers;

import com.szalai.account.core.events.BaseEvent;

public interface EventProducer {
    void produce(String topic, BaseEvent event);
}
