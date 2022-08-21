package com.szalai.account.cmd.api.commands;

import com.szalai.account.core.commands.BaseCommand;
import lombok.Data;

@Data
public class WithdrawFundsCommand extends BaseCommand {

    private Double amount;
}
