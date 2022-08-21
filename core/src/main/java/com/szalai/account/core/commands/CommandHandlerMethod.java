package com.szalai.account.core.commands;

@FunctionalInterface
public interface CommandHandlerMethod <T extends BaseCommand>{
    void handle(T command);
}
