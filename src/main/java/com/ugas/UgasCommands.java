package com.ugas;

import com.ugas.command.Command;
import com.ugas.command.CommandBuilder;

public class UgasCommands {

  public static Command<String, Integer> inCommand =
    new CommandBuilder().code("IN!").timeout(10).build();

  public static Command<String, Integer> in() {
    return inCommand;
  }

  public static Command<String, Integer> dwa() {
    return new CommandBuilder()
      .code("DWA!")
      .timeout(10)
      .build();
  }

}
//    new AsyncCommandDecorator(inCommand).async().
//    return Command.<IntegerData>builder().code("DWA").serialASCIIProtocol(crPacketProtocol)
//      .data(IntegerData.builder().value(dosis).build())
//      .dataFormater(IntegerFormater.builder().maximumIntegerDigits(1).build())
//      .build();
