package com.javafx.multitask;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.util.Duration.seconds;

public class MultiTaskPanel extends Application implements Initializable {

  @FXML
  private Button mpStartButton;

  @FXML
  private Button mpStopButton;

  @FXML
  private Label mpValueLabel;

  @FXML
  private Label mpStateLabel;

  @FXML
  private Button cpStartButton;

  @FXML
  private Button cpStopButton;

  @FXML
  private Label cpValueLabel;

  @FXML
  private Label cpStateLabel;

  private MilliVoltsPoll mpoll = new MilliVoltsPoll();

  private ChamberPoll cpoll = new ChamberPoll();

  @Override
  public void start(Stage stage) throws Exception {
    Scene rootScene = new Scene(FXMLLoader.load(getClass().getResource("MultiTaskPanel.fxml")));
    stage.setScene(rootScene);
    stage.show();
  }

  @Override
  public void initialize(final URL url, final ResourceBundle resourceBundle) {
    mpoll.setPeriod(seconds(1));
    cpoll.setPeriod(seconds(1));

    bindMilliVoltsPollControls();
    bindChamberPollControls();
  }

  private void bindMilliVoltsPollControls() {
    mpStateLabel.textProperty().bind(mpoll.stateProperty().asString());
    mpValueLabel.textProperty().bind(mpoll.valueProperty().asString());

    mpStartButton.setOnAction(event -> mpoll.start());
    mpStopButton.setOnAction(event -> mpoll.cancel());
  }

  private void bindChamberPollControls() {
    cpStateLabel.textProperty().bind(cpoll.stateProperty().asString());
    cpValueLabel.textProperty().bind(cpoll.valueProperty().asString());

    cpStartButton.setOnAction(event -> cpoll.start());
    cpStopButton.setOnAction(event -> cpoll.cancel());
  }

}
