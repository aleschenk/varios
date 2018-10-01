package com.printer.swing.ticket;

public class Utils {

  private static final double ONE_INCH_IN_MM = 25.4;
  private static final int PRINTER_UNITS = 72;
  private static final int SCREEN_PPI = 96;

  public static final double toDPI(final double millimeter) {
    return (millimeter / ONE_INCH_IN_MM) * PRINTER_UNITS;
  }

  // SCREEN RESOLUTION 1/96 inch
  // --------------------------------------------- 1 INCH  ---------------------------------------------------
  // ----------|----------|----------|----------|----------|----------|----------|----------|----------|------
  public static final int toPixels(final double printerDPI) {
    return SCREEN_PPI / (int) printerDPI;
  }

  // PRINTER RESOLUTION 1/72 inch
  // -------------------------------- 1 INCH  --------------------------------------
  // ----------|----------|----------|----------|----------|----------|----------|--
  public static final double toDPI(final Millimeter millimeter) {
    return millimeter.toInches() * PRINTER_UNITS;
  }

  public static final double inches(final int millimeters) {
    return millimeters / ONE_INCH_IN_MM;
  }

  public static final double pixelsToMillimeters(final int pixels) {
    return (pixels * ONE_INCH_IN_MM) / SCREEN_PPI;
  }

  public static final double millimetersToPixels(final int millimeters) {
    return (millimeters * SCREEN_PPI) / ONE_INCH_IN_MM;
  }

  public static class Millimeter {
    private final double value;
    private Millimeter(final double value) {
      this.value = value;
    }
    public static Millimeter from(final double value) {
      return new Millimeter(value);
    }
    public double toInches() {
      return value / ONE_INCH_IN_MM;
    }
  }

}
