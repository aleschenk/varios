package com.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.ConnectException;

public class Tutorial6CommunicationWindows extends Application {

  private Stage window;
  private Button button;

  public static void main(final String[] args) {
    launch(args);
  }

  @Override
  public void start(final Stage primaryStage) {
    setUserAgentStylesheet(STYLESHEET_CASPIAN);
    window = primaryStage;
    window.setTitle("thenewboston");

    button = new Button("Click Me");
    button.setOnAction(event -> {
//      boolean result = ConfirmBox.display("Yes or No", "Answer by Yes o No");
      boolean result = ConfirmBox.display("", "", e -> System.out.println("You hit the yes button"), e -> System.out.println("You hit de no button"));
      System.out.println(result);
    });

    StackPane layout = new StackPane();
    layout.getChildren().add(button);
    Scene scene = new Scene(layout, 300, 250);

    window.setScene(scene);
    window.show();

  }
}
