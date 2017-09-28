package com.ugas.parsers;

public class MeasureData {

	protected Magnitude magnitude;
	protected boolean errorInestability = false;
	protected boolean errorGain = false;
	protected boolean errorAdc = false;
	protected boolean errorInverted = false;

	public MeasureData() {
		super();
	}

	public Magnitude getMagnitude() {
		return magnitude;
	}

	protected void setMagnitude(Magnitude magnitude) {
		this.magnitude = magnitude;
	}

	@Override
	public String toString() {
		return "AbstractMeasureData [magnitude=" + magnitude + ", errorInestability=" + errorInestability
				+ ", errorGain=" + errorGain + ", errorAdc=" + errorAdc + ", errorInverted=" + errorInverted + "]";
	}

	public String printableFormat() {
    final StringBuilder sb = new StringBuilder();
    sb.append("errorInestability=").append(errorInestability);
    sb.append(", errorGain=").append(errorGain);
    sb.append(", errorAdc=").append(errorAdc);
    sb.append(", errorInverted=").append(errorInverted);
    return sb.toString();
  }

}