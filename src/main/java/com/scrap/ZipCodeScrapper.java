//package com.scrap;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class ZipCodeScrapper {
//
//  private static final Logger log = LoggerFactory.getLogger(ZipCodeScrapper.class);
//
//  private static final Pattern namePattern = Pattern.compile("(= ')(.*?<)");
//  private static final Pattern zipCodePattern = Pattern.compile("C\\d+");
//
//  public static class ZipCode implements Comparable<ZipCode> {
//    private final String city;
//    private final String zipCode;
//
//    public ZipCode(final String city, final String zipCode) {
//      this.city = city;
//      this.zipCode = zipCode;
//    }
//
//    @Override
//    public int compareTo(final ZipCode otherString) {
//      return city.compareTo(otherString.city());
//    }
//
//    public String city() {
//      return city;
//    }
//  }
//
//  public static void main(String[] args) throws Exception {
//
//    List<ZipCode> zipCodes = new ArrayList<ZipCode>();
//
//    Files.list(Paths.get("/home/ale/proyectos/iunigo/zipcodes")).forEach(file -> {
//
//      Document doc = null;
//      try {
//        doc = Jsoup.parse(file.toFile(), "UTF-8");
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//
//      Elements newsHeadlines = doc.select(".line");
//      for (Element headline : newsHeadlines) {
//
//        String onClickValue = headline.attr("onclick");
//
//        String zipCode = "", name = "";
//
//        Matcher nameMatcher = namePattern.matcher(onClickValue);
//        if (nameMatcher.find()) {
//          name = nameMatcher.group(2).replace("<", "");
//        }
//
//        Matcher zipCodeMatcher = zipCodePattern.matcher(onClickValue);
//        if (zipCodeMatcher.find()) {
//          zipCode = zipCodeMatcher.group().replace("C", "");
//        }
//
//        zipCodes.add(new ZipCode(name, zipCode));
//      }
//
////      zipCodes.sort(null);
//      Collections.sort(zipCodes);
//
//    });
//
//    List<String> lines = new ArrayList();
//    zipCodes.stream().forEach(zipCode -> {
//      lines.add(zipCode.city() + "," + zipCode.zipCode);
////      log.info("{} {}", zipCode.city(), zipCode.zipCode);
//    });
//
//    Files.write(Paths.get("./zipcodes.csv"), lines);
//  }
//
//}
