package com.spark;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import scala.Tuple2;

import java.util.Arrays;

public class SparkMapReduceExample {

  public void init() {
//    JavaRDD<String> textFile = sc.textFile("hdfs://...");
//    JavaPairRDD<String, Integer> counts = textFile
//      .flatMap(s -> Arrays.asList(s.split(" ")).iterator())
//      .mapToPair(word -> new Tuple2<>(word, 1))
//      .reduceByKey((a, b) -> a + b);
//    counts.saveAsTextFile("hdfs://...");
  }


  public static void main(String[] args) {
    new SparkMapReduceExample().init();

  }

}
