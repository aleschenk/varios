package com.server;

import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import spark.Session;

import static spark.Spark.init;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;
import static spark.Spark.webSocket;

public class MyServer {

  @WebSocket
  static class SensorWebSocketHandler {

    private String sender, msg;

    @OnWebSocketConnect
    public void onConnect(final Session session) {
    }

    @OnWebSocketClose
    public void onClose() {
    }

    @OnWebSocketMessage
    public void onMessage(final Session user, final String message) {
//      broadcastMessage(sender = Chat.userUsernameMap.get(user), msg = message);
    }

  }

  static void broadcastMessage(final String sender, final String message) {

  }


  public static void main(String[] args) {
    port(8080);

    staticFileLocation("/public");

    webSocket("/ws/sensor", SensorWebSocketHandler.class);

    post("/api/sensor/cycle/start", (req, res) -> {
      System.out.println("Start Cycle: " + req.body());
      res.status(201);
      return "";
    });

    post("/api/sensor/cycle/end", (req, res) -> {
      System.out.println("End Cycle: " + req.body());
      res.status(201);
      return "";
    });

    init();
  }

}
