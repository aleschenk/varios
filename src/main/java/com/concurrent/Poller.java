package com.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Poller {
  private static final Logger log = LoggerFactory.getLogger(Poller.class);

  public class Poll implements Runnable {
    @Override
    public void run() {
      log.info("Polling");
    }
  }

  public void init() throws InterruptedException {
    ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    ScheduledFuture<?> future = executor.scheduleAtFixedRate(new Poll(), 0, 500, TimeUnit.MILLISECONDS);
    TimeUnit.SECONDS.sleep(5);
    future.cancel(true);
    executor.shutdown();
  }

  public static void main(String[] args) throws InterruptedException {
    new Poller().init();
  }

}
