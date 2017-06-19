package com.algo;

import com.ugas.command.Command;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class CommandTest {

  @Test
  public void aCommandWithNegativeTimeoutShouldFail() {
    try {
      new Command("IN!", -1);
      fail("Timeout should not be negative.");
    } catch (final IllegalArgumentException e) {
      assertThat(e.getMessage(), is("The timeout for the command IN! is -1. It cannot be negative"));
    }
  }

  @Test
  public void aCommandWithTimeoutZeroShouldPass() {
    new Command("IN!", 0);
  }

}
