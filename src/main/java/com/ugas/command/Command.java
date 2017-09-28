package com.ugas.command;

import com.ugas.command.async.AsyncCommand;
import com.ugas.command.blocking.BlockingCommand;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public final class Command<PARAMETER_TYPE, RESPONSE_TYPE> {

  private final String code;

  private final int timeout;

  public Command(final String aCode, final int aTimeout) {
    checkNotNull(aCode);
    checkArgument(!aCode.isEmpty(), "The code cannot be empty");
    checkArgument(aTimeout >= 0, "The timeout for the command " + aCode + " is " + aTimeout
      + ". It cannot be negative");

    code = aCode;

    timeout = aTimeout;
  }

  public AsyncCommand<PARAMETER_TYPE, RESPONSE_TYPE> async() {
    return new AsyncCommand();
  }

  public BlockingCommand<PARAMETER_TYPE, RESPONSE_TYPE> blocking() {
    return new BlockingCommand(null);
  }

}
