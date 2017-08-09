package com.ugas.serial;

public interface SerialConnector {

    void connect();

    void close();

    void send();

    void read();

}
