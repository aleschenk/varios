package com.ugas;

import com.ugas.parsers.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Parsers {

  private static final Logger log = LoggerFactory.getLogger(Parsers.class);

  public static Function<String, OnePointData> toOnePointData = data -> {
    // pH= -73.03 ,CO2= 45.42 ! S,O2= 1.20 ! S
    Scanner scanner = new Scanner(data);
    try {
      scanner.useDelimiter(",|\r");

      OnePointData.OnePointDataBuilder onePointDataBuilder = OnePointData.builder();

      while (scanner.hasNext()) {

        Measure1PointData measure1PointData = Stream.of(scanner.next()).map(Measure1PointParser.parse).findFirst()
          .get();

        onePointDataBuilder.addMeasure1Point(measure1PointData);
      }
      return onePointDataBuilder.build();
    } finally {
      scanner.close();
    }
  };

  public static Function<String, TwoPointCalibration> twoPointCalibration = data -> {
    String splittedData[] = data.split("\r");

    OnePointData high = Parsers.toOnePointData.apply(splittedData[0]);
    OnePointData low = Parsers.toOnePointData.apply(splittedData[1]);
//    TwoPointData twoPoint = Parsers.twoPointData.apply(workaroudToRemoveCommandCodeFromResponse(splittedData[2]));

//    Stream streamOfHigh = high.stream();
//    Stream streamOfLow = low.stream();

    high.stream().collect(Collectors.toMap(
      (Function<Object, Object>) key -> null,
      (Function<Object, Object>) value -> null));

    Stream<Map.Entry<Magnitude, Measure1PointData>> entryStream = Stream.of(high.measures(), low.measures())
      .map(Map::entrySet)
      .flatMap(Collection::stream);

    entryStream.forEach(new Consumer<Map.Entry<Magnitude, Measure1PointData>>() {
      @Override
      public void accept(final Map.Entry<Magnitude, Measure1PointData> entry) {
        log.info("{} {}\n", entry.getKey(), entry.getValue());
      }
    });


    return new TwoPointCalibration(high, low, null);
  };

  public static void main(String[] args) {

    String data = "pH=   33.37,CO2=   -6.69,O2=   -0.55\r" +
      " pH=    2.02,CO2=  -28.63,O2=  -25.70\r" +
      "KMA  pH=   58:    2.24    ,CO2=   73:  -28.68    ,O2= 125:  -25.70\r";

    twoPointCalibration.apply(data);


  }

}
