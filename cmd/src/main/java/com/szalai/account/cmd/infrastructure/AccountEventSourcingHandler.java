package com.szalai.account.cmd.infrastructure;

import com.szalai.account.cmd.domain.AccountAggregate;
import com.szalai.account.core.domain.AggregateRoot;
import com.szalai.account.core.events.BaseEvent;
import com.szalai.account.core.handlers.EventSourcingHandler;
import com.szalai.account.core.infrastructure.EventStore;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountEventSourcingHandler implements EventSourcingHandler<AccountAggregate> {

    private final EventStore eventStore;

    @Override
    public void save(AggregateRoot aggregate) {
        eventStore.saveEvents(aggregate.getId(), aggregate.getUncommittedChanges(), aggregate.getVersion());
        aggregate.markChangesAsCommitted();
    }

    @Override
    public AccountAggregate getById(String id) {
        AccountAggregate aggregate = new AccountAggregate();
        List<BaseEvent> events = eventStore.getEvents(id);
        if (events != null && !events.isEmpty()){
            aggregate.replayEvents(events);
            Optional<Integer> version = events.stream().map(BaseEvent::getVersion).max(Comparator.naturalOrder());
            aggregate.setVersion(version.get());
        }

        return aggregate;
    }
}
