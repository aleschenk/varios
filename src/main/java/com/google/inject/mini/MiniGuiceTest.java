package com.google.inject.mini;

import com.google.inject.Provides;

import javax.inject.Inject;

public class MiniGuiceTest {

  public static class AppModule {

    @Provides
    @Inject
    public A newA(final B b) {
      System.out.println("B " + b);
      return new A(b);
    }

    @Provides
    public B newB() {
      return new B();
    }

  }

  public static class B {
    public B() {
      System.out.println("B");
    }
  }


  public static class A {
    @Inject
    public A(final B b) {
      System.out.println("B " + b);
      System.out.println("Aa");
    }
  }

  public static void main(String[] args) {

    A a = MiniGuice.inject(A.class, new AppModule());



  }

}
