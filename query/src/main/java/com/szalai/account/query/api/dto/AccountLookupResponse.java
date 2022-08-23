package com.szalai.account.query.api.dto;

import com.szalai.account.common.dto.BaseResponse;
import com.szalai.account.query.domain.BankAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountLookupResponse extends BaseResponse {

    private List<BankAccount> accounts;

    public AccountLookupResponse(String message) {
        super(message);
    }

    public AccountLookupResponse(List<BankAccount> accounts, String message) {
        super(message);
        this.accounts = accounts;
    }
}
