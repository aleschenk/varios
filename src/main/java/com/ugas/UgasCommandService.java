package com.ugas;

import com.ugas.command.Command;
import com.ugas.serial.SerialConnector;

public class UgasCommandService {

    private final SerialConnector serialConnector;

    public UgasCommandService(final SerialConnector aSerialConnector) {
        serialConnector = aSerialConnector;
    }

    public int kba(final String algo) {
//        return CommandParser.kba(serialConnector.send());
        return 0;
    }

    public void send(final Command command) {
        serialConnector.send();
    }

}
