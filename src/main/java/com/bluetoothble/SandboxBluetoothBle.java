package com.bluetoothble;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tinyb.BluetoothDevice;
import tinyb.BluetoothManager;

import java.util.List;

public class SandboxBluetoothBle {

  private static final Logger log = LoggerFactory.getLogger(SandboxBluetoothBle.class);

  public static void main(String[] args) throws InterruptedException {
    BluetoothManager manager = BluetoothManager.getBluetoothManager();

    if (manager.getDiscovering()) {
      log.info("Stop Discovery");
      manager.stopDiscovery();
    }
//    boolean discoveryStarted = manager.startDiscovery();
//    getDevice("xs");
//    manager.startNearbyDiscovery(new BluetoothManager.BluetoothDeviceDiscoveryListener() {
//      @Override
//      public void bluetoothDeviceDiscovered(BluetoothDevice bluetoothDevice) {
//
//      }
//    }, 10, true);


//    System.out.println("The discovery started: " + (discoveryStarted ? "true" : "false"));
//    BluetoothDevice sensor = getDevice( args[0]);

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
    System.out.print("Address = " + device.getAddress());
    System.out.print(" Name = " + device.getName());
    System.out.print(" Connected = " + device.getConnected());
    System.out.println();
  }


}
