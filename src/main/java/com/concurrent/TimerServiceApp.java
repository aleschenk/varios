package com.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimerServiceApp extends Application {

  private static Logger log = LoggerFactory.getLogger(TimerServiceApp.class);

  @Override
  public void start(Stage stage) throws Exception {
    TimerService service = new TimerService();
    AtomicInteger count = new AtomicInteger(0);
    service.setCount(count.get());
    service.setPeriod(Duration.seconds(1));
    service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

      @Override
      public void handle(WorkerStateEvent t) {
        log.info("Called : {} time(s) " + t.getSource().getValue());
        count.set((int) t.getSource().getValue());
      }
    });
    service.start();
  }

  public static void main(String[] args) {
    launch();
  }

  private static class TimerService extends ScheduledService<Integer> {
    private IntegerProperty count = new SimpleIntegerProperty();

    public final void setCount(Integer value) {
      count.set(value);
    }

    public final Integer getCount() {
      return count.get();
    }

    public final IntegerProperty countProperty() {
      return count;
    }

    protected Task<Integer> createTask() {
      log.info("createTask");
      return new Task<Integer>() {
        protected Integer call() {
          log.info("What");
          //Adds 1 to the count
          count.set(getCount() + 1);
          return getCount();
        }
      };
    }
  }
}