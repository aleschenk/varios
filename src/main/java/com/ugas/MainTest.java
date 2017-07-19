package com.ugas;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.ugas.command.UgasError;
import com.ugas.command.async.SuccessCallback;

public class MainTest {

  public static class UgasModule extends AbstractModule {
    @Override
    protected void configure() {
    }
   }

  public static void main(String[] args) throws Exception {

    Guice.createInjector();

    new MainTest().init();
  }

  private void init() throws Exception {
//    UgasCommands.async().onSuccess(null).onError(null).onTimeout(null).in().send();
//    UgasCommands.blocking().in().send();

//    UgasCommands.async().in().onSuccess().onError().onTimeout().send();
//    UgasCommands.blocking().in().send();

    UgasCommands.in().async().onSuccess(System.out::println);

    UgasCommands.in().async().onSuccess(System.out::println).onError(this::handleUgasErrors).onTimeout(null).send(null);
    Integer value = UgasCommands.dwa().blocking().send("");

  }

  public void handleUgasErrors(final UgasError error) {

  }

}
