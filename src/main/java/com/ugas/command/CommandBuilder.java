package com.ugas.command;

public class CommandBuilder<T, R> {

  private String code;

  private int timeout;

  public CommandBuilder code(final String code) {
    this.code = code;
    return this;
  }

  public CommandBuilder timeout(final int timeout) {
    this.timeout = timeout;
    return this;
  }

  public CommandBuilder paramters() {
    return this;
  }

  public Command<T, R> build() {
    return new Command<>(code, timeout);
  }

}
