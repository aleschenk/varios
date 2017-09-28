package com.ugas.parsers;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;
import java.util.function.Function;

public class Measure2PointParser {
  protected static final String UGAS_INTERFACE_CMD_ERROR_RESULT_ADC = "?";
  protected static final String UGAS_INTERFACE_CMD_ERROR_RESULT_GAIN = "S";
  protected static final String UGAS_INTERFACE_CMD_ERROR_RESULT_INESTABILITY = "/";
  protected static final String UGAS_INTERFACE_CMD_ERROR_RESULT_INVERTED = "!";

  public static Function<String, Measure2PointData> parse = data -> {
    // TODO capturar los errores

    // case UGAS_INTERFACE_CMD_ERROR_RESULT_ADC: (?)
    // xErrors |= UGAS_INTERFACE_ERROR_RESULT_ADC; break;
    // case UGAS_INTERFACE_CMD_ERROR_RESULT_GAIN: (S)
    // xErrors |= UGAS_INTERFACE_ERROR_RESULT_GAIN; break;
    // case UGAS_INTERFACE_CMD_ERROR_RESULT_INESTABILITY: (\\)
    // xErrors |= UGAS_INTERFACE_ERROR_RESULT_INESTABILITY; break;
    // case UGAS_INTERFACE_CMD_ERROR_RESULT_INVERTED: (!)
    // xErrors |= UGAS_INTERFACE_ERROR_RESULT_INVERTED; break;
    //
    boolean errorInestability = false;
    boolean errorGain = false;
    boolean errorAdc = false;
    boolean errorInverted = false;

    // pH= 77: -75.50 ,CO2= 2: 48.16 ,O2= 2: 1.09
    Scanner scanner = new Scanner(data.trim());
    try {
      scanner.useDelimiter("\\s*=\\s*|\\s\\s*|\\s*:\\s*");
      scanner.useLocale(Locale.ENGLISH);

      // \s*=\s*|\s\s*

      if (!scanner.hasNext()) {
        throw new RuntimeException();
      }
      String measureCode = scanner.next();
      Magnitude magnitude = Magnitude.valueOf(measureCode);

      if (!scanner.hasNextBigDecimal()) {
        // throw new RuntimeException();
      }
      BigDecimal value1 = scanner.nextBigDecimal();
      if (!scanner.hasNextBigDecimal()) {
        // throw new RuntimeException();
      }
      BigDecimal value2 = scanner.nextBigDecimal();

      while (scanner.hasNext()) {
        String flag = scanner.next();
        if (flag.equals(UGAS_INTERFACE_CMD_ERROR_RESULT_ADC)) {
          errorAdc = true;
        } else if (flag.equals(UGAS_INTERFACE_CMD_ERROR_RESULT_GAIN)) {
          errorGain = true;
        } else if (flag.equals(UGAS_INTERFACE_CMD_ERROR_RESULT_INESTABILITY)) {
          errorInestability = true;
        } else if (flag.equals(UGAS_INTERFACE_CMD_ERROR_RESULT_INVERTED)) {
          errorInverted = true;
        }
      }
      return Measure2PointData.builder().magnitude(magnitude).value1(value1).value2(value2).errorAdc(errorAdc)
          .errorGain(errorGain).errorInestability(errorInestability).errorInverted(errorInverted).build();
    } finally {
      scanner.close();
    }
  };

}
