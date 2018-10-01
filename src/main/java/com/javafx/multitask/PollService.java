package com.javafx.multitask;

//import com.dyngr.Poller;
//import com.dyngr.PollerBuilder;
//import com.dyngr.core.*;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PollService extends Service<Integer> {

  private static final Logger log = LoggerFactory.getLogger(PollService.class);

  Task task = new Task() {
    @Override
    protected Object call() throws Exception {
      while(true) {
        log.info("sleeping");
        executeCommand();
        log.info("Wake up");
      }
    }
  };

  private void executeCommand() {
    Sleep.seconds(10);
  }

  public void startPoll() {
    restart();
    log.info("Poll started");
  }

  public void stopPoll() {
    cancel();
    log.info("Poll Stopped");
  }

  @Override
  protected Task<Integer> createTask() {
    log.info("Creating new task");
    return task;
  }

  public void createPOller() throws ExecutionException, InterruptedException {
//    Poller<String> poller = PollerBuilder.<String>newBuilder()
//      .withWaitStrategy(WaitStrategies.fixedWait(1, TimeUnit.SECONDS))
//      .withStopStrategy(StopStrategies.neverStop())
//      .polling(new AttemptMaker<String>() {
//        @Override
//        public AttemptResult<String> process() throws Exception {
//          return AttemptResults.justContinue();
//        }
//      })
//      .build();
//
//    poller.start();
  }


  ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

  public void init() {
    Runnable task = () -> log.info("Scheduling: " + System.nanoTime());

    int initialDelay = 0;
    int period = 1;
    executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);
  }

  public void pausePoll() {

  }

  public void shutdownPoller() {
    executor.shutdown();
  }

}
