package de.onvif.beans;

import java.io.Serializable;

public class DeviceInfo implements Serializable {
  private String id;
  private String manufacturer;
  private String model;
  private String firmwareVersion;
  private String serialNumber;
  private String hardwareId;
  private String ip;
  private boolean isAlive = true;
  private String playHost;
  public DeviceInfo(){}
  public DeviceInfo(
      String manufacturer,
      String model,
      String firmwareVersion,
      String serialNumber,
      String hardwareId) {
    super();
    this.manufacturer = manufacturer;
    this.model = model;
    this.firmwareVersion = firmwareVersion;
    this.serialNumber = serialNumber;
    this.hardwareId = hardwareId;
  }

  @Override
  public String toString() {
    return "DeviceInfo [manufacturer="
        + manufacturer
        + ", model="
        + model
        + ", firmwareVersion="
        + firmwareVersion
        + ", serialNumber="
        + serialNumber
        + ", hardwareId="
        + hardwareId
        + "]";
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getFirmwareVersion() {
    return firmwareVersion;
  }

  public void setFirmwareVersion(String firmwareVersion) {
    this.firmwareVersion = firmwareVersion;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public String getHardwareId() {
    return hardwareId;
  }

  public void setHardwareId(String hardwareId) {
    this.hardwareId = hardwareId;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public boolean getIsAlive() {
    return isAlive;
  }

  public void setIsAlive(boolean isAlive) {
    this.isAlive = isAlive;
  }

  public boolean isAlive() {
    return isAlive;
  }

  public void setAlive(boolean alive) {
    isAlive = alive;
  }

  public String getPlayHost() {
    return playHost;
  }

  public void setPlayHost(String playHost) {
    this.playHost = playHost;
  }
}
