package com.concurrent;

import java.util.function.Supplier;

public class PollSandbox {

  public static final class Poll {
    private final long period;

    private final Supplier supplier;

    public Poll(long period, final Supplier supplier) {
      this.period = period;
      this.supplier = supplier;
    }

    public void start() {
    }

    public void stop() {
    }
  }


  PollSandbox() {
    Poll poll = new Poll(100, () -> {
      return "";
    });

    poll.start();
    poll.stop();
  }

}
