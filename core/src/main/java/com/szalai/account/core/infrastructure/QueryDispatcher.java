package com.szalai.account.core.infrastructure;

import com.szalai.account.core.domain.BaseEntity;
import com.szalai.account.core.queries.BaseQuery;
import com.szalai.account.core.queries.QueryHandlerMethod;

import java.util.List;

public interface QueryDispatcher {
    <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler);
    <U extends BaseEntity> List<U> send(BaseQuery query);
}
