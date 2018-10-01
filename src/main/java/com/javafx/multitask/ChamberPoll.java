package com.javafx.multitask;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChamberPoll extends ScheduledService {

  private static final Logger log = LoggerFactory.getLogger(com.javafx.multitask.ChamberPoll.class);

  private static class ChamberPollTask extends Task<Integer> {
    private final UgasCommander ugasCommander;

    public ChamberPollTask() {
      this.ugasCommander = new UgasCommander();
    }

    @Override
    protected Integer call() {
      Integer value = ugasCommander.send();
      updateValue(value);
      return value;
    }
  }

  @Override
  protected Task createTask() {
    return new ChamberPollTask();
  }

}
