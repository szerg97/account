package com.szalai.account.query.api.queries;

import com.szalai.account.core.queries.BaseQuery;
import com.szalai.account.query.api.dto.EqualityType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccountsWithBalanceQuery extends BaseQuery {

    private EqualityType equalityType;
    private Double balance;
}
