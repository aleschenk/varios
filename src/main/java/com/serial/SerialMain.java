package com.serial;

import com.fazecast.jSerialComm.SerialPort;

import java.util.Scanner;

public class SerialMain {

  private static SerialPort serialPort;

  public static void main(final String[] args) {
    if("info".equalsIgnoreCase(args[0])) {
      printSerialPortInfo();
    } else {

      serialPort = SerialPort.getCommPort(args[0]); // /dev/ttyUSB0

      while (true) {
        System.out.print("Command: ");
        Scanner in = new Scanner(System.in);
        String command = in.nextLine().toLowerCase();

        if ("exit".equalsIgnoreCase(command)) {
          closePort();
          System.exit(0);
        }

        sendCommand(command);
      }
    }

  }

  public static void sendCommand(final String command) {
    System.out.println("Sending command: " + command);

    if(!serialPort.isOpen()) {
      System.out.println("Opening port");
      serialPort.openPort();
    }

    if(!serialPort.isOpen()) {
      System.out.println("Unable to send the command, the port is close.");
    } else {
      serialPort.writeBytes(command.getBytes(), command.length());
    }
  }

  public static void closePort() {
    if(serialPort.isOpen()) {
      serialPort.closePort();
    }
  }

  public static void printSerialPortInfo() {
    SerialPort[] serialPorts = SerialPort.getCommPorts();
    for (SerialPort serialPort : serialPorts) {
      System.out.println("Baud Rate: " + serialPort.getBaudRate());
      System.out.println("Descriptive Port Name: " + serialPort.getDescriptivePortName());
      System.out.println("Flow Control Settings: " + serialPort.getFlowControlSettings());
      System.out.println("Num Data Bits: " + serialPort.getNumDataBits());
      System.out.println("Num Stop Bits: " + serialPort.getNumStopBits());
      System.out.println("Parity: " + serialPort.getParity());
      System.out.println("Read Timeout: " + serialPort.getReadTimeout());
      System.out.println("System Port Name: " + serialPort.getSystemPortName());
      System.out.println("Write Timeout: " + serialPort.getWriteTimeout());
      System.out.println("----------------------------------");
    }
  }

}
