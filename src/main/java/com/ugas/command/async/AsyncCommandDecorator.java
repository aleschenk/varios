package com.ugas.command.async;

import com.ugas.command.Command;

public class AsyncCommandDecorator {

  private final Command command;

  public AsyncCommandDecorator(final Command command) {
    this.command = command;
  }

  public AsyncCommand async() {
    return new AsyncCommand();
  }

}
