package com.ugas.parsers;

import java.math.BigDecimal;

public class Measure1PointData extends MeasureData {

	private BigDecimal value;

	public Measure1PointData() {
		super();
	}

	public Measure1PointData(Measure1PointDataBuilder measure1PointDataBuilder) {
		magnitude = measure1PointDataBuilder.magnitude;
		value = measure1PointDataBuilder.value;
		errorAdc = measure1PointDataBuilder.errorAdc;
		errorGain = measure1PointDataBuilder.errorGain;
		errorInestability = measure1PointDataBuilder.errorInestability;
		errorInverted = measure1PointDataBuilder.errorInverted;
	}

	public BigDecimal getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value.toPlainString();
	}

	public String printableFormat() {
	  return value + " " + super.printableFormat();
  }

	public static Measure1PointDataBuilder builder() {
		return new Measure1PointDataBuilder();
	}

	public static class Measure1PointDataBuilder {
		private BigDecimal value;
		private Magnitude magnitude;
		private boolean errorInestability = false;
		private boolean errorGain = false;
		private boolean errorAdc = false;
		private boolean errorInverted = false;

		public Measure1PointDataBuilder value(final BigDecimal val) {
			value = val;
			return this;
		}

		public Measure1PointDataBuilder magnitude(final Magnitude value) {
			magnitude = value;
			return this;
		}

		public Measure1PointDataBuilder errorInestability(boolean value) {
			errorInestability = value;
			return this;
		}

		public Measure1PointDataBuilder errorGain(boolean value) {
			errorGain = value;
			return this;
		}

		public Measure1PointDataBuilder errorAdc(boolean value) {
			errorAdc = value;
			return this;
		}

		public Measure1PointDataBuilder errorInverted(boolean value) {
			errorInverted = value;
			return this;
		}

		public Measure1PointData build() {
			return new Measure1PointData(this);
		}
	}
}
