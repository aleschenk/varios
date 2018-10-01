package com.javafx.multitask;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MilliVoltsPoll extends ScheduledService {

  private static final Logger log = LoggerFactory.getLogger(MilliVoltsPoll.class);

  private static class MiliPollTask extends Task<Integer> {
    private final UgasCommander ugasCommander;

    public MiliPollTask() {
      this.ugasCommander = new UgasCommander();
    }

    @Override
    protected Integer call() {
      Integer result = ugasCommander.send();
      log.info("result: {}", result);
      updateValue(result);
      Sleep.seconds(1);
      return 1;
    }

    @Override
    protected void cancelled() {
      super.cancelled();
      updateMessage("The task was cancelled.");
    }

    protected void failed() {
      super.failed();
      updateMessage("The task failed.");
    }

    @Override
    public void succeeded() {
      super.succeeded();
      updateMessage("The task finished successfully.");
    }

  }

  @Override
  protected Task createTask() {
    return new MiliPollTask();
  }

}