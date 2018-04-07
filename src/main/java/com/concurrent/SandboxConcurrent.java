package com.concurrent;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@ThreadSafe
public class SandboxConcurrent {

  public static class PrimerGenerator implements Runnable {

    @GuardedBy("this")
    private final List<BigInteger> primes = new ArrayList();

    private volatile boolean cancelled;

    @Override
    public void run() {
      BigInteger p = BigInteger.ONE;

      while(!cancelled) {
        p = p.nextProbablePrime();
        synchronized (this) {
          primes.add(p);
        }
      }
    }

    public void cancel() { cancelled = true; }

    public synchronized List<BigInteger> get() {
      return new ArrayList(primes);
    }

  }

  public static void main(String[] args) {
    PrimerGenerator p = new PrimerGenerator();
    new Thread(p).start();
    p.cancel();

  }

}
