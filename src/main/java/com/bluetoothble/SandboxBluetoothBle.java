package com.bluetoothble;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tinyb.BluetoothDevice;
import tinyb.BluetoothGattService;
import tinyb.BluetoothManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Function;

import static com.bluetoothble.Colors.ANSI_BLUE;
import static com.bluetoothble.Colors.ANSI_RESET;
import static java.util.stream.Collectors.toList;

public class SandboxBluetoothBle extends Application implements Initializable {

  private static final Logger log = LoggerFactory.getLogger(SandboxBluetoothBle.class);

  /*
   * To start looking of the device, we first must initialize the TinyB library. The way of interacting with the
   * library is through the BluetoothManager. There can be only one BluetoothManager at one time, and the
   * reference to it is obtained through the getBluetoothManager method.
   */
  BluetoothManager manager = BluetoothManager.getBluetoothManager();

  @FXML
  private Button scanButton;

  @FXML
  private Label statusLabel;

  @FXML
  private TableView<DeviceRow> deviceTable;
  @FXML
  private TableColumn<DeviceRow, String> nameColumn;
  @FXML
  private TableColumn<DeviceRow, String> addressColumn;
  @FXML
  private TableColumn<DeviceRow, Boolean> connectedColumn;
  @FXML
  private TableColumn<DeviceRow, String> servicesColumn;
  @FXML
  private TableColumn<DeviceRow, String> adapterColumn;
  @FXML
  private TableColumn<DeviceRow, String> aliasColumn;
  @FXML
  private TableColumn<DeviceRow, Boolean> blockedColumn;
  @FXML
  private TableColumn<DeviceRow, Number> bluetoothClassColumn;
  @FXML
  private TableColumn<DeviceRow, String> bluetoothTypeColumn;
  @FXML
  private TableColumn<DeviceRow, String> iconColumn;
  @FXML
  private TableColumn<DeviceRow, Boolean> legacyPairingColumn;
  @FXML
  private TableColumn<DeviceRow, String> manufacturerDataColumn;
  @FXML
  private TableColumn<DeviceRow, String> modaliasColumn;
  @FXML
  private TableColumn<DeviceRow, Boolean> pairedColumn;
  @FXML
  private TableColumn<DeviceRow, Number> rssiColumn;
  @FXML
  private TableColumn<DeviceRow, String> serviceDataColumn;
  @FXML
  private TableColumn<DeviceRow, Boolean> trustedColumn;
  @FXML
  private TableColumn<DeviceRow, Number> txPowerColumn;
  @FXML
  private TableColumn<DeviceRow, String> uuidColumn;

  private boolean discoveryStarted = false;

  @Override
  public void start(final Stage stage) throws Exception {
    Scene rootScene = new Scene(FXMLLoader.load(getClass().getResource("SandboxBluetoothBle.fxml")));
    stage.setScene(rootScene);
    stage.show();
  }

  @Override
  public void initialize(final URL location, final ResourceBundle resources) {
    scanButton.setOnAction(event -> turnOnOffScan());
    nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
    connectedColumn.setCellValueFactory(cellData -> cellData.getValue().connectedProperty());
    servicesColumn.setCellValueFactory(cellData -> cellData.getValue().servicesProperty());
    adapterColumn.setCellValueFactory(cellData -> cellData.getValue().adapterProperty());
    aliasColumn.setCellValueFactory(cellData -> cellData.getValue().aliasProperty());
    blockedColumn.setCellValueFactory(cellData -> cellData.getValue().blockedProperty());
    bluetoothClassColumn.setCellValueFactory(cellData -> cellData.getValue().bluetoothClassProperty());
    bluetoothTypeColumn.setCellValueFactory(cellData -> cellData.getValue().bluetoothTypeProperty());
    iconColumn.setCellValueFactory(cellData -> cellData.getValue().iconProperty());
    legacyPairingColumn.setCellValueFactory(cellData -> cellData.getValue().legacyPairingProperty());
    manufacturerDataColumn.setCellValueFactory(cellData -> cellData.getValue().manufacturerDataProperty());
    modaliasColumn.setCellValueFactory(cellData -> cellData.getValue().modaliasProperty());
    pairedColumn.setCellValueFactory(cellData -> cellData.getValue().pairedProperty());
    rssiColumn.setCellValueFactory(cellData -> cellData.getValue().rssiProperty());
    serviceDataColumn.setCellValueFactory(cellData -> cellData.getValue().serviceDataProperty());
    trustedColumn.setCellValueFactory(cellData -> cellData.getValue().trustedProperty());
    txPowerColumn.setCellValueFactory(cellData -> cellData.getValue().txPowerProperty());
    uuidColumn.setCellValueFactory(cellData -> cellData.getValue().uuidProperty());
  }

  private void turnOnOffScan() {
    if (discoveryStarted) {
      stopScan();
    } else {
      scan();
    }
  }

  private final Function<BluetoothDevice, DeviceRow> toDeviceRow =
    reportLine -> new DeviceRow(reportLine);

  private void scan() {
    scanButton.setText("Stop scan");
    statusLabel.setText("Status: Scanning");
    // The manager will try to initialize a BluetoothAdapter if any adapter is present in the system. To initialize
    // discovery we can call startDiscovery, which will put the default adapter in discovery mode.
    discoveryStarted = manager.startDiscovery();

    List<BluetoothDevice> list = manager.getDevices();
    if (list == null)
      return;

    for (final BluetoothDevice device : list) {
      printDevice(device);
    }

    ObservableList<DeviceRow> data =
      FXCollections.observableArrayList(list
        .stream()
        .map(toDeviceRow)
        .collect(toList()));

    deviceTable.setItems(data);
  }

  private void stopScan() {
    scanButton.setText("Start Scan");
    statusLabel.setText("Status: Not Scanning");
    discoveryStarted = false;
    manager.stopDiscovery();
  }

  private void connect() {
//    BluetoothDevice sensor = getDevice(args[0]);
//    BluetoothGattService tempService = getService(sensor, "f000aa00-0451-4000-b000-000000000000");
  }

  static BluetoothDevice getDevice(String address) throws InterruptedException {
    BluetoothManager manager = BluetoothManager.getBluetoothManager();
    BluetoothDevice sensor = null;
    for (int i = 0; (i < 15); ++i) {
      List<BluetoothDevice> list = manager.getDevices();
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
      Thread.sleep(4000);
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
