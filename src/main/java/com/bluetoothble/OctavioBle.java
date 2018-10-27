package com.bluetoothble;

import eu.hansolo.medusa.Gauge;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tinyb.BluetoothDevice;
import tinyb.BluetoothException;
import tinyb.BluetoothGattCharacteristic;
import tinyb.BluetoothGattService;
import tinyb.BluetoothManager;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.bluetoothble.Colors.ANSI_BLUE;
import static com.bluetoothble.Colors.ANSI_RESET;

public class OctavioBle extends Application implements Initializable {

  private static final Logger log = LoggerFactory.getLogger(OctavioBle.class);

  @FXML
  private AnchorPane rootPane;

  private GridPane pane;

  @FXML
  private Button getEnergyButton;

  @FXML
  private Button consumeEnergyButton;

  @FXML
  private Button disconnectButton;

  @FXML
  private Gauge energyGauge;

  @Override
  public void start(final Stage stage) throws IOException {
    Scene rootScene = new Scene(FXMLLoader.load(getClass().getResource("OctavioBle.fxml")));
    stage.setScene(rootScene);
    stage.show();
  }

  @Override
  public void initialize(final URL location, final ResourceBundle resources) {
    energyGauge.setUnit("ENERGY");
    energyGauge.setSkinType(Gauge.SkinType.SLIM);
    energyGauge.setDecimals(0);
    energyGauge.setValue(200);
    energyGauge.setValue(1);

    VBox stepsBox = getTopicBox("ENERGY", Color.rgb(77, 208, 225), energyGauge);
    rootPane.setBackground(new Background(new BackgroundFill(Color.rgb(39, 44, 50), CornerRadii.EMPTY, Insets.EMPTY)));
//    rootPane.add(stepsBox, 0, 0);
    rootPane.getChildren().add(stepsBox);
    getEnergyButton.setOnAction(event -> getEnergy());
    consumeEnergyButton.setOnAction(event -> consumeEnergy());
    disconnectButton.setOnAction(evenet -> disconnect());
  }

  BluetoothManager manager = BluetoothManager.getBluetoothManager();

  BluetoothDevice sensor;

  boolean discoveryStarted;

  private void getEnergy() {
    if (sensor == null || !sensor.getConnected()) {
      log.info("Sensor not connected");

      discoveryStarted = manager.startDiscovery();

      log.info("The discovery started: " + (discoveryStarted ? "true" : "false"));
      sensor = getDevice("7C:EC:79:EB:35:7A");

      /*
       * After we find the device we can stop looking for other devices.
       */
      try {
        manager.stopDiscovery();
      } catch (BluetoothException e) {
        log.error("Discovery could not be stopped.", e);
      }

      if (sensor == null) {
        log.info("No sensor found with the provided address.");
        System.exit(-1);
      }

      log.info("Found device: ");
//    printDevice(sensor);

      if (sensor.connect()) {
        log.info("Sensor with the provided address connected");
      } else {
        log.info("Could not connect device.");
        System.exit(-1);
      }
    }

    BluetoothGattService octavioSensor = getService(sensor, "0000ffe0-0000-1000-8000-00805f9b34fb");

    if (octavioSensor == null) {
      log.info("This device does not have the temperature service we are looking for.");
      sensor.disconnect();
      System.exit(-1);
    }
    System.out.println("Found service " + octavioSensor.getUUID());

    BluetoothGattCharacteristic tempValue = getCharacteristic(octavioSensor, "0000ffe1-0000-1000-8000-00805f9b34fb");
    tempValue.writeValue("GE\n".getBytes());

    byte[] a = tempValue.readValue();

    for (byte d : a) {
      log.info("d: {}", (int) d);
    }

    log.info("VALUE: {}", new String(a));
  }

  private void consumeEnergy() {
    energyGauge.setValue(0);
  }

  private void disconnect() {
    sensor.disconnect();
  }

//  @Override
//  public void init() {
//    VBox stepsBox = getTopicBox("ENERGY", Color.rgb(77, 208, 225), energyGauge);
//
//    pane = new GridPane();
//    pane.setPadding(new Insets(20));
//    pane.setHgap(10);
//    pane.setVgap(15);
//    pane.setBackground(new Background(new BackgroundFill(Color.rgb(39, 44, 50), CornerRadii.EMPTY, Insets.EMPTY)));
//    pane.add(stepsBox, 0, 0);
//  }

  @Override
  public void stop() {
    System.exit(0);
  }

  private VBox getTopicBox(final String TEXT, final Color COLOR, final Gauge GAUGE) {
    Rectangle bar = new Rectangle(200, 3);
    bar.setArcWidth(6);
    bar.setArcHeight(6);
    bar.setFill(COLOR);

    Label label = new Label(TEXT);
    label.setTextFill(COLOR);
    label.setAlignment(Pos.CENTER);
    label.setPadding(new Insets(0, 0, 10, 0));

    GAUGE.setBarColor(COLOR);
    GAUGE.setBarBackgroundColor(Color.rgb(39, 44, 50));
    GAUGE.setAnimated(true);

    VBox vBox = new VBox(bar, label, GAUGE);
    vBox.setSpacing(3);
    vBox.setAlignment(Pos.CENTER);
    return vBox;
  }

  static boolean running = true;

  /*
   * After discovery is started, new devices will be detected. We can get a list of all devices through the manager's
   * getDevices method. We can the look through the list of devices to find the device with the MAC which we provided
   * as a parameter. We continue looking until we find it, or we try 15 times (1 minutes).
   */
  static BluetoothDevice getDevice(String address) {
    BluetoothManager manager = BluetoothManager.getBluetoothManager();
    BluetoothDevice sensor = null;
    for (int i = 0; (i < 15) && running; ++i) {
      List<BluetoothDevice> list = manager.getDevices();
      if (list == null)
        return null;

      for (BluetoothDevice device : list) {
        printDevice(device);
        /*
         * Here we check if the address matches.
         */
        if (device.getAddress().equals(address))
          sensor = device;
      }

      if (sensor != null) {
        return sensor;
      }
      try {
        Thread.sleep(4000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  /*
   * Our device should expose a temperature service, which has a UUID we can find out from the data sheet. The service
   * description of the SensorTag can be found here:
   * http://processors.wiki.ti.com/images/a/a8/BLE_SensorTag_GATT_Server.pdf. The service we are looking for has the
   * short UUID AA00 which we insert into the TI Base UUID: f000XXXX-0451-4000-b000-000000000000
   */
  private static BluetoothGattService getService(final BluetoothDevice device, final String UUID) {
    System.out.println("Services exposed by device:");
    BluetoothGattService tempService = null;
    List<BluetoothGattService> bluetoothServices = null;
    do {
      bluetoothServices = device.getServices();
      if (bluetoothServices == null)
        return null;

      for (BluetoothGattService service : bluetoothServices) {
        System.out.println("UUID: " + service.getUUID());
        if (service.getUUID().equals(UUID))
          tempService = service;
      }
      try {
        Thread.sleep(4000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    } while (bluetoothServices.isEmpty() && running);
    return tempService;
  }

  private static BluetoothGattCharacteristic getCharacteristic(BluetoothGattService service, String UUID) {
    List<BluetoothGattCharacteristic> characteristics = service.getCharacteristics();
    if (characteristics == null)
      return null;

    for (BluetoothGattCharacteristic characteristic : characteristics) {
      if (characteristic.getUUID().equals(UUID))
        return characteristic;
    }
    return null;
  }


  static void printDevice(BluetoothDevice device) {
    log.info("Name: {}", device.getName());
    log.info("Address: {}", device.getAddress());
    log.info("Connected: {}", device.getConnected());
    log.info("{}Services{}: ", ANSI_BLUE, ANSI_RESET);
    device.getServices().stream().forEach(service -> {
      log.info("\t{}UUID{}: {}", ANSI_BLUE, ANSI_RESET, service.getUUID());
      log.info("\tType: {}", service.getBluetoothType());
      log.info("\tPrimary: {}", service.getPrimary());
      log.info("\tCharacteristics:");
      service.getCharacteristics().forEach(characteristic -> {
        log.info("\t\tUUID: {}", characteristic.getUUID());
        log.info("\t\tBluetoothType: {}", characteristic.getBluetoothType());
//        log.info("\t\tService.Characteristics.Value: {}", new String(characteristic.getValue()));
        log.info("\t\tNotifying: {}", characteristic.getNotifying());
        log.info("\t\tDescriptors:");
        characteristic.getDescriptors().forEach(descriptor -> {
          log.info("\t\t\tUUID: {}", descriptor.getUUID());
//          log.info("\t\t\tService.Characteristics.Descriptors.Value: {}", new String(descriptor.getValue()));
        });
        log.info("");
      });
    });
    log.info("Adapter: {}", device.getAdapter());
    log.info("Alias: {}", device.getAlias());
    log.info("Blocked: {}", device.getBlocked());
    log.info("BluetoothClass: {}", device.getBluetoothClass());
    log.info("BluetoothType: {}", device.getBluetoothType());
    log.info("Icon: {}", device.getIcon());
    log.info("LegacyPairing: {}", device.getLegacyPairing());
    log.info("ManufacturerData: {}", device.getManufacturerData());
    log.info("Modalias: {}", device.getModalias());
    log.info("Paired: {}", device.getPaired());
    log.info("RSSI: {}", device.getRSSI());
    log.info("ServiceData: {}", device.getServiceData());
    log.info("Trusted: {}", device.getTrusted());
    log.info("TxPower: {}", device.getTxPower());
    log.info("UUIDs: {}", device.getUUIDs());
  }

}
