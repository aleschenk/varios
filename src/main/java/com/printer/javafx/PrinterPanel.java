package com.printer.javafx;

import javafx.application.Application;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class PrinterPanel extends Application implements Initializable {

  private static final Logger log = LoggerFactory.getLogger(PrinterPanel.class);

  @FXML
  private Pane previewPane;

  @FXML
  private Button printButton;

  @FXML
  private Button printCanvasButton;

  @FXML
  private Button printSceneButton;

  @Override
  public void start(final Stage stage) throws Exception {
    Scene rootScene = new Scene(FXMLLoader.load(getClass().getResource("PrinterPanel.fxml")));
    stage.setScene(rootScene);
    stage.show();
  }

  private Node ticket() {
    TextArea text = new TextArea();
    text.setPrefRowCount(10);
    text.setPrefColumnCount(20);
    text.setWrapText(true);
    text.setText("XXXXXXXX");
    return text;
  }

  private Node scene() {
    VBox root = new VBox(5);
    Label textLbl = new Label("Text:");
    TextArea text = new TextArea();
    text.setPrefRowCount(10);
    text.setPrefColumnCount(20);
    text.setWrapText(true);
    root.getChildren().addAll(textLbl, text);
    return root;
  }

  private Node canvas() {
    Canvas canvas = new Canvas(100, 100);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.setLineWidth(1.0);
    gc.setStroke(Color.BLACK);
    gc.strokeText("Hello World", 0, 10);
    gc.strokeText("Drawing Text", 100, 10, 40);
    return canvas;
  }

  @Override
  public void initialize(final URL location, final ResourceBundle resources) {
    printDefaultPrinter();
    printListOfPrinters();
    previewPane.getChildren().add(canvas());
    printButton.setOnAction(event -> print(ticket()));
    printCanvasButton.setOnAction(event -> print(canvas()));
    printSceneButton.setOnAction(event -> print(scene()));
  }

  private void printListOfPrinters() {
    log.info("List of printers");
    ObservableSet<Printer> allPrinters = Printer.getAllPrinters();
    allPrinters.stream().forEach(printer -> {
      log.info("Printer: {}", printer.getName());
    });
  }

  private Optional<Printer> findPrinterByName(final String printerName) {
    return Printer.getAllPrinters().stream()
      .filter(printer -> printer.getName().equals(printerName)).findFirst();
  }

  public void print(final Node node) {
    String printerName = "DiestroPrinter";
    Optional<Printer> printerOptional = findPrinterByName(printerName);
    if(!printerOptional.isPresent()) {
      log.warn("Printer {} not found.", printerName);
      return ;
    }

    Printer printer = printerOptional.get();

    PrinterJob printerJob = PrinterJob.createPrinterJob();
    printerJob.setPrinter(printer);
    boolean proceed = printerJob.showPageSetupDialog(null);
    if(proceed) {
      boolean printed = printerJob.printPage(node);
      if (printed) {
        printerJob.endJob();
      } else {
        log.error("Printing failed.");
      }
    }
  }

  private void printDefaultPrinter() {
    Printer defaultPrinter = Printer.getDefaultPrinter();
    log.info("Default Printer: {}", defaultPrinter == null ? "There is not a default printer. " : defaultPrinter.getName() );
  }

}
