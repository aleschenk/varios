package com.javafx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {

  static boolean answer;
  static Stage window = new Stage();

  public static boolean display(final String title, final String message) {
    return display(title, message,
      event -> { answer = true; window.close(); },
      event -> { answer = false; window.close(); }
      );
  }

  public static boolean display(final String title, final String message, final EventHandler<ActionEvent> onYesAction
    , final EventHandler<ActionEvent> onNoAction) {

    window.initModality(Modality.APPLICATION_MODAL);
    window.setTitle(title);
    window.setMinHeight(250);

    Label label = new Label();
    label.setText(message);

    Button yesButton = new Button("Yes");
    yesButton.setOnAction(onYesAction);

    Button noButton = new Button("No");
    noButton.setOnAction(onNoAction);

    VBox layout = new VBox(10);
    layout.getChildren().addAll(label, yesButton, noButton);
    layout.setAlignment(Pos.CENTER);

    Scene scene = new Scene(layout);
    window.setScene(scene);

    window.showAndWait();

    return answer;
  }

}
