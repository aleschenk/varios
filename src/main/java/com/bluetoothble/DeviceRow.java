package com.bluetoothble;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import tinyb.BluetoothDevice;

import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class DeviceRow {

  private SimpleStringProperty name;
  private SimpleStringProperty address;
  private SimpleBooleanProperty connected;
  private SimpleStringProperty services;
  private SimpleStringProperty adapter;
  private SimpleStringProperty alias;
  private SimpleBooleanProperty blocked;
  private SimpleIntegerProperty bluetoothClass;
  private SimpleStringProperty bluetoothType;
  private SimpleStringProperty icon;
  private SimpleBooleanProperty legacyPairing;
  private SimpleStringProperty manufacturerData;
  private SimpleStringProperty modalias;
  private SimpleBooleanProperty paired;
  private SimpleIntegerProperty rssi;
  private SimpleStringProperty serviceData;
  private SimpleBooleanProperty trusted;
  private SimpleIntegerProperty txPower;
  private SimpleStringProperty uuid;

  public DeviceRow(final BluetoothDevice device) {
    name = new SimpleStringProperty(device.getName());
    address = new SimpleStringProperty(device.getAddress());
    connected = new SimpleBooleanProperty(device.getConnected());
    services = new SimpleStringProperty(""); // new SimpleStringProperty(device.getServices());
    adapter = new SimpleStringProperty(); // new SimpleStringProperty(device.getAdapter());
    alias = new SimpleStringProperty(device.getAlias());
    blocked = new SimpleBooleanProperty(device.getBlocked());
    bluetoothClass = new SimpleIntegerProperty(device.getBluetoothClass());
    bluetoothType = new SimpleStringProperty(); // new SimpleStringProperty(device.getBluetoothType());
    icon = new SimpleStringProperty(device.getIcon());
    legacyPairing = new SimpleBooleanProperty(device.getLegacyPairing());
    manufacturerData = new SimpleStringProperty(); // new SimpleStringProperty(device.getManufacturerData());
    modalias = new SimpleStringProperty(device.getModalias());
    paired = new SimpleBooleanProperty(device.getPaired());
    rssi = new SimpleIntegerProperty(device.getRSSI());
    serviceData = new SimpleStringProperty(); // new SimpleStringProperty(device.getServiceData());
    trusted = new SimpleBooleanProperty(device.getTrusted());
    txPower = new SimpleIntegerProperty(device.getTxPower());
    uuid = new SimpleStringProperty(asList(device.getUUIDs()).stream().collect(Collectors.joining("|")));
  }

  public String getName() {
    return name.get();
  }

  public SimpleStringProperty nameProperty() {
    return name;
  }

  public void setName(String name) {
    this.name.set(name);
  }

  public String getAddress() {
    return address.get();
  }

  public SimpleStringProperty addressProperty() {
    return address;
  }

  public void setAddress(String address) {
    this.address.set(address);
  }

  public boolean isConnected() {
    return connected.get();
  }

  public SimpleBooleanProperty connectedProperty() {
    return connected;
  }

  public void setConnected(boolean connected) {
    this.connected.set(connected);
  }

  public String getServices() {
    return services.get();
  }

  public SimpleStringProperty servicesProperty() {
    return services;
  }

  public void setServices(String services) {
    this.services.set(services);
  }

  public String getAdapter() {
    return adapter.get();
  }

  public SimpleStringProperty adapterProperty() {
    return adapter;
  }

  public void setAdapter(String adapter) {
    this.adapter.set(adapter);
  }

  public String getAlias() {
    return alias.get();
  }

  public SimpleStringProperty aliasProperty() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias.set(alias);
  }

  public boolean isBlocked() {
    return blocked.get();
  }

  public SimpleBooleanProperty blockedProperty() {
    return blocked;
  }

  public void setBlocked(boolean blocked) {
    this.blocked.set(blocked);
  }

  public int getBluetoothClass() {
    return bluetoothClass.get();
  }

  public SimpleIntegerProperty bluetoothClassProperty() {
    return bluetoothClass;
  }

  public void setBluetoothClass(int bluetoothClass) {
    this.bluetoothClass.set(bluetoothClass);
  }

  public String getBluetoothType() {
    return bluetoothType.get();
  }

  public SimpleStringProperty bluetoothTypeProperty() {
    return bluetoothType;
  }

  public void setBluetoothType(String bluetoothType) {
    this.bluetoothType.set(bluetoothType);
  }

  public String getIcon() {
    return icon.get();
  }

  public SimpleStringProperty iconProperty() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon.set(icon);
  }

  public boolean isLegacyPairing() {
    return legacyPairing.get();
  }

  public SimpleBooleanProperty legacyPairingProperty() {
    return legacyPairing;
  }

  public void setLegacyPairing(boolean legacyPairing) {
    this.legacyPairing.set(legacyPairing);
  }

  public String getManufacturerData() {
    return manufacturerData.get();
  }

  public SimpleStringProperty manufacturerDataProperty() {
    return manufacturerData;
  }

  public void setManufacturerData(String manufacturerData) {
    this.manufacturerData.set(manufacturerData);
  }

  public String getModalias() {
    return modalias.get();
  }

  public SimpleStringProperty modaliasProperty() {
    return modalias;
  }

  public void setModalias(String modalias) {
    this.modalias.set(modalias);
  }

  public boolean isPaired() {
    return paired.get();
  }

  public SimpleBooleanProperty pairedProperty() {
    return paired;
  }

  public void setPaired(boolean paired) {
    this.paired.set(paired);
  }

  public int getRssi() {
    return rssi.get();
  }

  public SimpleIntegerProperty rssiProperty() {
    return rssi;
  }

  public void setRssi(int rssi) {
    this.rssi.set(rssi);
  }

  public String getServiceData() {
    return serviceData.get();
  }

  public SimpleStringProperty serviceDataProperty() {
    return serviceData;
  }

  public void setServiceData(String serviceData) {
    this.serviceData.set(serviceData);
  }

  public boolean isTrusted() {
    return trusted.get();
  }

  public SimpleBooleanProperty trustedProperty() {
    return trusted;
  }

  public void setTrusted(boolean trusted) {
    this.trusted.set(trusted);
  }

  public int getTxPower() {
    return txPower.get();
  }

  public SimpleIntegerProperty txPowerProperty() {
    return txPower;
  }

  public void setTxPower(int txPower) {
    this.txPower.set(txPower);
  }

  public String getUuid() {
    return uuid.get();
  }

  public SimpleStringProperty uuidProperty() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid.set(uuid);
  }
}
