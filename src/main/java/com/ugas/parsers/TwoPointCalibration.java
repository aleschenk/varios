package com.ugas.parsers;

public class TwoPointCalibration {

  private final OnePointData high;

  private final OnePointData low;

  private final TwoPointData calibration;

  public TwoPointCalibration(final OnePointData high, final OnePointData low, final TwoPointData calibration) {
    this.high = high;
    this.low = low;
    this.calibration = calibration;
  }

  public OnePointData high() {
    return high;
  }

  public OnePointData low() {
    return low;
  }

  public TwoPointData calibration() {
    return calibration;
  }

  public String printableFormat() {
    return high.printableFormat() + "\n" + low.printableFormat() + "\n" + calibration.printableFormat();
  }

  @Override
  public String toString() {
    return "Calibration [high=" + high + ", low=" + low + ", calibration=" + calibration + "]";
  }

}
