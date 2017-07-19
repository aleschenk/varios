package com.ugas.command;

import com.ugas.command.async.AsyncCommand;
import com.ugas.command.blocking.BlockingCommand;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public final class Command<PARAMETER_TYPE, RESPONSE_TYPE> {

  private final String code;

  private final int timeout;

  public Command(final String code, final int timeout) {
    checkNotNull(code);
    checkArgument(!code.isEmpty(), "The code cannot be empty");
    checkArgument(timeout >= 0, "The timeout for the command " + code + " is " + timeout
      + ". It cannot be negative");
    this.code = code;
    this.timeout = timeout;
  }

  public AsyncCommand<PARAMETER_TYPE, RESPONSE_TYPE> async() {
    return new AsyncCommand();
  }

  public BlockingCommand<PARAMETER_TYPE, RESPONSE_TYPE> blocking() {
    return new BlockingCommand(null);
  }

}
