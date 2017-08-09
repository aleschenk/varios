package com.retrofit;

import static java.util.concurrent.TimeUnit.SECONDS;
import static spark.Spark.get;
import static spark.Spark.port;

public class RetrofitServerTest {

  public static void main(String[] args) {
    port(8080);

    get("/hello", (req, res) -> {
      System.out.println("Request: hello.");
      return "Hello World";
    });

    get("/error", (request, response) -> {
      System.out.println("Request: error");
      response.status(500);
      return "";
    });

    get("/bad", (request, response) -> {
      System.out.println("Request: bad");
      response.status(400);
      return "";
    });

    get("/timeout", (request, response) -> {
      System.out.println("Request: timeout");
      waitFor(4);
      response.status(200);
      return "";
    });

  }

  static void waitFor(int seconds) {
    try {
      System.out.println("Waiting " + seconds + " seconds");
      Thread.sleep(SECONDS.toMillis(4));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}


