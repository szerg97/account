package com.szalai.account.cmd.api.commands;

import com.szalai.account.common.dto.AccountType;
import com.szalai.account.core.commands.BaseCommand;
import lombok.Data;

@Data
public class OpenAccountCommand extends BaseCommand {

    private String accountHolder;
    private AccountType accountType;
    private Double openingBalance;
}
