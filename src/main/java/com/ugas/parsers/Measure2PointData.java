package com.ugas.parsers;

import java.math.BigDecimal;

public class Measure2PointData extends MeasureData {

	private BigDecimal value1;
	private BigDecimal value2;

	public Measure2PointData() {
		super();
	}

	public Measure2PointData(Measure2PointDataBuilder measure2PointDataBuilder) {
		magnitude = measure2PointDataBuilder.magnitude;
		value1= measure2PointDataBuilder.value1;
		value2= measure2PointDataBuilder.value2;
		errorAdc = measure2PointDataBuilder.errorAdc;
		errorGain = measure2PointDataBuilder.errorGain;
		errorInestability = measure2PointDataBuilder.errorInestability;
		errorInverted = measure2PointDataBuilder.errorInverted;
	}

	public BigDecimal getValue1() {
		return value1;
	}

	public BigDecimal getValue2() {
		return value2;
	}

	@Override
  public String printableFormat() {
    final StringBuilder sb = new StringBuilder();
    sb.append("value1=").append(value1);
    sb.append(", value2=").append(value2);
    sb.append(", ").append(super.printableFormat());
    return sb.toString();
  }

	@Override
	public String toString() {
		return "Measure2PointData [value1=" + value1 + ", value2=" + value2 + ", toString()=" + super.toString() + "]";
	}
	
	public static Measure2PointDataBuilder builder(){
		return new Measure2PointDataBuilder();
	}

	public static class Measure2PointDataBuilder {
		private BigDecimal value1;
		private BigDecimal value2;
		private Magnitude magnitude;
		private boolean errorInestability = false;
		private boolean errorGain = false;
		private boolean errorAdc = false;
		private boolean errorInverted = false;

		public Measure2PointDataBuilder value1(final BigDecimal val) {
			value1 = val;
			return this;
		}

		public Measure2PointDataBuilder value2(final BigDecimal val) {
			value2 = val;
			return this;
		}

		public Measure2PointDataBuilder magnitude(final Magnitude value) {
			magnitude = value;
			return this;
		}

		public Measure2PointDataBuilder errorInestability(boolean value) {
			errorInestability = value;
			return this;
		}

		public Measure2PointDataBuilder errorGain(boolean value) {
			errorGain = value;
			return this;
		}

		public Measure2PointDataBuilder errorAdc(boolean value) {
			errorAdc = value;
			return this;
		}

		public Measure2PointDataBuilder errorInverted(boolean value) {
			errorInverted = value;
			return this;
		}

		public Measure2PointData build() {
			return new Measure2PointData(this);
		}
	}

}
