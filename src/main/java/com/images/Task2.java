package com.images;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Task2 {

  static class Point3D {
    public int x;
    public int y;
    public int z;

    public Point3D(int x, int y, int z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }
  }

  public static int solution(final Point3D[] points) {

    for(int i = 0; i < points.length; i++) {
       double radius = sqrt(pow(points[i].x, 2) + pow(points[i].y, 2) + pow(points[i].z, 2));
      System.out.println(radius);
    }

    return 1;
  }

  public static void main(String[] args) {
    Point3D[] points = {

    new Point3D(0, 5, 4),
    new Point3D(0, 0, -3),
    new Point3D(-2, 1, -6),
    new Point3D(1, -2, 2),
    new Point3D(1, 1, 1),
    new Point3D(4, -4, 3)};

    System.out.println(solution(points));
  }

}
