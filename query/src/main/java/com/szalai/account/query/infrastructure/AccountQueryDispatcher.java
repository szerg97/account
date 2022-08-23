package com.szalai.account.query.infrastructure;

import com.szalai.account.core.domain.BaseEntity;
import com.szalai.account.core.infrastructure.QueryDispatcher;
import com.szalai.account.core.queries.BaseQuery;
import com.szalai.account.core.queries.QueryHandlerMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class AccountQueryDispatcher implements QueryDispatcher {

    private final Map<Class<? extends BaseQuery>, List<QueryHandlerMethod>> routes = new HashMap<>();

    @Override
    public <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler) {
        List<QueryHandlerMethod> handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);
    }

    @Override
    public <U extends BaseEntity> List<U> send(BaseQuery query) {
        List<QueryHandlerMethod> handlers = routes.get(query.getClass());
        if(handlers == null || handlers.size() == 0){
            throw new RuntimeException("No query handler was registered");
        }
        if(handlers.size() > 1) {
            throw new RuntimeException("Cannot send query to more than one handler");
        }
        return handlers.get(0).handle(query);
    }
}
