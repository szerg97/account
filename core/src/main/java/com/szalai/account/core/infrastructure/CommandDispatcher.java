package com.szalai.account.core.infrastructure;

import com.szalai.account.core.commands.BaseCommand;
import com.szalai.account.core.commands.CommandHandlerMethod;

public interface CommandDispatcher  {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
    void send(BaseCommand command);
}
