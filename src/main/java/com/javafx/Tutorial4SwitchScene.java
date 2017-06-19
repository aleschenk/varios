package com.javafx;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Tutorial4SwitchScene extends Application {

  private Stage window;
  private Scene scene1, scene2;

  public static void main(final String[] args) {
    launch(args);
  }

  @Override
  public void start(final Stage primaryStage) {
    window = primaryStage;

    Label label1 = new Label("Welcome to the first scene!");
    Button button1 = new Button("Go to scene 2");
    button1.setOnAction(event -> window.setScene(scene2));

    VBox layout1 = new VBox(20);
    layout1.getChildren().addAll(label1, button1);
    scene1 = new Scene(layout1, 200, 200);

    Button button2 = new Button("Go to scene 1");
    button2.setOnAction(event -> window.setScene(scene1));

    StackPane layout2 = new StackPane();
    layout2.getChildren().addAll(button2);
    scene2 = new Scene(layout2, 600, 300);

    window.setScene(scene1);
    window.setTitle("xxx");
    window.show();
  }

}
