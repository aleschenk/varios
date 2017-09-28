package com.ugas.command.blocking;

import com.ugas.serial.SerialConnector;

public class BlockingCommand<PARAMETER_TYPE, RESPONSE_TYPE> {

  private final SerialConnector serialConnector;

  public BlockingCommand(final SerialConnector aSerialConnector) {
    serialConnector = aSerialConnector;
  }

  public RESPONSE_TYPE send(final PARAMETER_TYPE parameter) {
    return null;
  }

}
