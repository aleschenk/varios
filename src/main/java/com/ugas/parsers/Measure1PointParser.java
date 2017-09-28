package com.ugas.parsers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;
import java.util.function.Function;

public class Measure1PointParser {

  protected static Logger log = LoggerFactory.getLogger(Measure1PointParser.class);
  
  protected static final String UGAS_INTERFACE_CMD_ERROR_RESULT_ADC = "?";
  protected static final String UGAS_INTERFACE_CMD_ERROR_RESULT_GAIN = "S";
  protected static final String UGAS_INTERFACE_CMD_ERROR_RESULT_INESTABILITY = "/";
  protected static final String UGAS_INTERFACE_CMD_ERROR_RESULT_INVERTED = "!";

  public static Function<String, Measure1PointData> parse = data -> {
    

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

    // pH= -73.03 ,CO2= 45.42 ! S,O2= 1.20 ! S
    Scanner scanner = new Scanner(data.trim());
    try {
      scanner.useDelimiter("\\s*=\\s*|\\s\\s*");
      scanner.useLocale(Locale.ENGLISH);
      // \s*=\s*|\s\s*

      if (!scanner.hasNext()) {
        throw new RuntimeException();
      }
      String measureCode = scanner.next();

      Magnitude magnitude = Magnitude.valueOf(measureCode);
      
      // TODO eliminar este log
      log.debug("Magnitude : " + magnitude.getCodigo());

      if (!scanner.hasNextBigDecimal()) {
//        throw new RuntimeException("hasNextBigDecimal");
      }
      BigDecimal value = scanner.nextBigDecimal();
      
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
      return Measure1PointData.builder().magnitude(magnitude).value(value).errorAdc(errorAdc).errorGain(errorGain)
          .errorInestability(errorInestability).errorInverted(errorInverted).build();
    } finally {
      scanner.close();
    }
  };

}
