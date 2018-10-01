package com.javafx.multitask;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PollPanel extends Application implements Initializable {

  @FXML
  Button mvPollStartButton;

  @FXML
  Button mvPollStopButton;

  @FXML
  Button chPollStartButton;

  @FXML
  Button chPollStopButton;

  private PollService poll = new PollService();

  @Override
  public void start(Stage stage) throws Exception {
    Scene rootScene = new Scene(FXMLLoader.load(getClass().getResource("PollPanel.fxml")));
    stage.setScene(rootScene);
    stage.show();
  }

  @Override
  public void initialize(final URL url, final ResourceBundle resourceBundle) {
    mvPollStartButton.setOnAction(event -> poll.init());
//    mvPollStopButton.setOnAction(event -> poll.stopExecutor());
  }

}
