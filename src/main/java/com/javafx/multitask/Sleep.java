package com.javafx.multitask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Sleep {

  private static final Logger log = LoggerFactory.getLogger(Sleep.class);

  public static void seconds(final int seconds) {
    try {
      TimeUnit.SECONDS.sleep(seconds);
    } catch (final InterruptedException e) {
      log.error("Sleep Interrupted");
      throw new RuntimeException(e);
    }
  }

}
