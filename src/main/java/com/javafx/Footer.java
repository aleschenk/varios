package com.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Footer extends Application implements Initializable {

  @Override
  public void start(final Stage stage) throws Exception {
    Scene rootScene = new Scene(FXMLLoader.load(getClass().getResource("Footer.fxml")));
    stage.setScene(rootScene);
    stage.show();
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }

}
