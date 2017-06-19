package com.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Tutorial5AlertBoxes extends Application {

  private Stage window;
  private Button button;

  public static void main(final String[] args) {
    launch(args);
  }

  @Override
  public void start(final Stage primaryStage) {
    window = primaryStage;
    window.setTitle("thenewboston");

    button = new Button("Click Me");
    button.setOnAction(event -> AlertBox.display("Error", "You have an error"));

    StackPane layout = new StackPane();
    layout.getChildren().add(button);
    Scene scene = new Scene(layout, 300, 250);

    window.setScene(scene);
    window.show();

  }

}
