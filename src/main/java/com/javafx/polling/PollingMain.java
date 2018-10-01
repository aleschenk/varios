package com.javafx.polling;

import com.javafx.multitask.Sleep;

public class PollingMain {

  public static void main(final String[] args) throws Exception {
    new PollingMain().init();
  }

  public void init() throws Exception {
    Thread milliPoll = new Thread(new MilliPoll());
    Thread chamberPoll = new Thread(new ChamberPoll());
  }

  public static class MilliPoll implements Runnable {
    @Override
    public void run() {
      Sleep.seconds(1);
    }
  }

  public static class ChamberPoll implements Runnable {
    @Override
    public void run() {
    }
  }

}
