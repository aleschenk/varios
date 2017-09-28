package com.ugas.parsers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoPointData {

	private Map<Magnitude, Measure2PointData> measures = new HashMap<Magnitude, Measure2PointData>();

	public TwoPointData(TwoPointDataBuilder twoPointDataBuilder) {
		for (Measure2PointData measure2PointData : twoPointDataBuilder.measureData) {
//			Measure1PointData measure1PointData = measure1PointDataBuilder.build();
			measures.put(measure2PointData.magnitude, measure2PointData);
		}
	}

	public Measure2PointData getMeasure (Magnitude magnitude){
		return this.measures.get(magnitude);
	}

  public String printableFormat() {
    StringBuilder stringBuilder = new StringBuilder();
    measures.forEach((magnitude, measure2PointData) -> {
      stringBuilder.append(magnitude).append(":").append(measure2PointData.printableFormat()).append('\n');
    });

    return stringBuilder.toString();
  }

	@Override
	public String toString() {
		return "TwoPointData [measures=" + measures + "]";
	}

	public static TwoPointDataBuilder builder(){
		return new TwoPointDataBuilder();
	}
	
	public static class TwoPointDataBuilder{
		private List<Measure2PointData> measureData = new ArrayList<Measure2PointData>();
		
		public TwoPointDataBuilder addMeasure2Point(Measure2PointData measure2PointData){
			measureData.add(measure2PointData);
			return this;
		}
		
		public TwoPointData build(){
			return new TwoPointData(this);
		}
	}}
