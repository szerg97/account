package com.szalai.account.query.api.queries;

import com.szalai.account.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccountByIdQuery extends BaseQuery {

    private String id;
}
