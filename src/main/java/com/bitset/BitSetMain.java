package com.bitset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.BitSet;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

public class BitSetMain {

  private static final Logger log = LoggerFactory.getLogger(BitSetMain.class);

  public static final Integer NaBit = 0;
  public static final Integer KBit = 1;
  public static final Integer ClBit = 2;
  public static final Integer CaBit = 3;
  public static final Integer LiBit = 4;
  public static final Integer PhBit = 5;
  public static final Integer CO2Bit = 6;
  public static final Integer O2Bit = 7;

  public static void main(String[] args) {


    //  0
    // 128 64 32 16 8 4 2 1
    BitSet value = new BitSet();
    value.set(2);
    value.set(4);

    IntStream.range(0, 2).forEach(cnt -> {
      try {
        throw new RuntimeException("AAA");
      } catch (final Exception e) {
        log.error("", e);
      }
    });


//    System.out.println(value.get(0));
//    System.out.println(value.get(1));
//    System.out.println(value.get(2));
//    System.out.println(value.get(3));
//    System.out.println(value.get(4));
//    System.out.println(value.get(5));
//    System.out.println(value.get(6));
//    System.out.println(value.get(7));

//    value.stream().filter(value1 -> true).forEach(value12 -> System.out.println(value12));
//    long[] longs = value.toLongArray();

//    value.stream().mapToObj()

    System.out.println("");

//    value.stream().forEach(value13 -> System.out.println(value13));

  }

}
