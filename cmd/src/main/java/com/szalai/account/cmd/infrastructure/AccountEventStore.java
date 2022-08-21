package com.szalai.account.cmd.infrastructure;

import com.szalai.account.cmd.domain.AccountAggregate;
import com.szalai.account.cmd.domain.EventStoreRepository;
import com.szalai.account.core.events.BaseEvent;
import com.szalai.account.core.events.EventModel;
import com.szalai.account.core.exceptions.AggregateNotFoundException;
import com.szalai.account.core.exceptions.ConcurrencyException;
import com.szalai.account.core.infrastructure.EventStore;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountEventStore implements EventStore {

    private final EventStoreRepository repository;

    @Override
    public void saveEvents(String aggregateId, Iterable<BaseEvent> events, Integer expectedVersion) {
        List<EventModel> eventStream = repository.findByAggregateIdentifier(aggregateId);
        if (expectedVersion != -1 && eventStream.get(eventStream.size() - 1).getVersion() != expectedVersion){
            throw new ConcurrencyException();
        }

        Integer version = expectedVersion;
        for (BaseEvent event :
                events) {
            version++;
            event.setVersion(version);
            EventModel model = new EventModel(
                    new Date(),
                    aggregateId,
                    AccountAggregate.class.getTypeName(),
                    version,
                    event.getClass().getTypeName(),
                    event
            );
            EventModel persistedEvent = repository.save(model);
            if(persistedEvent != null){
                //TODO: produce event to Kafka
            }
        }
    }

    @Override
    public List<BaseEvent> getEvents(String aggregateId) {
        List<EventModel> eventStream = repository.findByAggregateIdentifier(aggregateId);
        if (eventStream == null || eventStream.isEmpty()){
            throw new AggregateNotFoundException("Incorrect account id provided");
        }

        return eventStream.stream().map(EventModel::getEventData).collect(Collectors.toList());
    }
}
