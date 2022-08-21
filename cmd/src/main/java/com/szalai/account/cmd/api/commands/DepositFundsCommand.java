package com.szalai.account.cmd.api.commands;

import com.szalai.account.core.commands.BaseCommand;
import lombok.Data;

@Data
public class DepositFundsCommand extends BaseCommand {

    private Double amount;
}
