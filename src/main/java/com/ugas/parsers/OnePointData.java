package com.ugas.parsers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class OnePointData {

	private Map<Magnitude, Measure1PointData> measures = new HashMap();
	
	public OnePointData(OnePointDataBuilder onePointDataBuilder) {
		for (Measure1PointData measure1PointData : onePointDataBuilder.measureData) {
			measures.put(measure1PointData.magnitude, measure1PointData);
		}
	}

	public Map<Magnitude, Measure1PointData> measures() {
	  return measures;
  }

	public Measure1PointData getMeasure (Magnitude magnitude){
		return this.measures.get(magnitude);
	}

	public Stream<Map.Entry<Magnitude, Measure1PointData>> stream() {
	  return measures.entrySet().stream();
  }

	@Override
	public String toString() {
		return "OnePointData [measures=" + measures + "]";
	}

  public String printableFormat() {
    StringBuilder stringBuilder = new StringBuilder();
    measures.forEach((magnitude, measure1PointData) -> {
      stringBuilder.append(magnitude).append(":").append(measure1PointData.printableFormat()).append('\n');
    });

    return stringBuilder.toString();
  }

	public static OnePointDataBuilder builder(){
		return new OnePointDataBuilder();
	}
	
	public static class OnePointDataBuilder{
		private List<Measure1PointData> measureData = new ArrayList();
		
		public OnePointDataBuilder addMeasure1Point(Measure1PointData measure1PointData){
			measureData.add(measure1PointData);
			return this;
		}
		
		public OnePointData build(){
			return new OnePointData(this);
		}
	}
	
}
