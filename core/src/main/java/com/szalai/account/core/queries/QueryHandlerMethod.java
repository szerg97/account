package com.szalai.account.core.queries;

import com.szalai.account.core.domain.BaseEntity;

import java.util.List;

@FunctionalInterface
public interface QueryHandlerMethod <T extends BaseQuery> {
    List<BaseEntity> handle(T query);
}
